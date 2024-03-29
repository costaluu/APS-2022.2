package com.ufpe.aps.communication;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.IsNotOwnerOfProductException;
import com.ufpe.aps.exception.ProdutoNotFoundException;
import com.ufpe.aps.pojo.AvaliacaoDTO;
import com.ufpe.aps.produto.FachadaProduto;
import com.ufpe.aps.produto.Produto;
import com.ufpe.aps.repository.bdr.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${produto.servlet.path}")
public class ComunicacaoProduto {

    @Value("${produto.api-key}")
    private String apiKey;

    private final FachadaProduto fachadaProduto;

    @Autowired
    public ComunicacaoProduto(Environment env, ProdutoDAO repository) {
        this.fachadaProduto = new FachadaProduto(env, repository);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos() {
        return ResponseEntity.ok(fachadaProduto.pegarTodosProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable("id") String idProduto, @RequestParam int quantidade) throws ProdutoNotFoundException {
        return ResponseEntity.ok(fachadaProduto.pegarProduto(idProduto, quantidade));
    }

    @GetMapping("/meus-produtos/{login}")
    public ResponseEntity<List<Produto>> getAllMyProdutos(@PathVariable String login) {
        return ResponseEntity.ok(fachadaProduto.meusProdutos(login));
    }

    @PostMapping("/publicar")
    public ResponseEntity<Produto> publicarProduto(@RequestBody Produto produto) {
        try{
            fachadaProduto.publicarItem(produto);
            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> deletarProduto(@RequestParam String login, @PathVariable("idProduto") String idProduto) throws IsNotOwnerOfProductException {
        if (login == null || login.isEmpty())
            throw new IllegalArgumentException("Login não informado");

        fachadaProduto.excluirProduto(login, idProduto);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/avaliar")
    public ResponseEntity<Void> avaliarProduto(AvaliacaoDTO avaliacaoDTO) {
        if (avaliacaoDTO == null || avaliacaoDTO.getLogin().isEmpty() || avaliacaoDTO.getIdProduto().isEmpty() || avaliacaoDTO.getAvaliacao() == null)
            throw new IllegalArgumentException("Login não informado");

        if(avaliacaoDTO.getAvaliacao() < 0 || avaliacaoDTO.getAvaliacao() > 5)
            throw new IllegalArgumentException("Avaliação deve estar entre 0 e 5");

        fachadaProduto.avaliar(avaliacaoDTO.getLogin(), avaliacaoDTO.getIdProduto(), avaliacaoDTO.getAvaliacao());
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/atualiza-estoque")
    public ResponseEntity<Void> atualizaEstoque(@RequestHeader("X-API-KEY") String apiKey, @RequestBody Carrinho carrinho) {
        if(apiKey == null || apiKey.isEmpty())
            throw new IllegalArgumentException("API KEY não informada");
        if(!apiKey.equals(this.apiKey))
            throw new IllegalArgumentException("API KEY inválida");

        fachadaProduto.atualizarEstoque(carrinho);
        return ResponseEntity.noContent().build();
    }
}
