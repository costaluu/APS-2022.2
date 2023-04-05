package com.ufpe.aps.communication;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.IsNotOwnerOfProductException;
import com.ufpe.aps.exception.ProdutoNotFoundException;
import com.ufpe.aps.exception.QuantidadeProdutoException;
import com.ufpe.aps.pojo.AvaliacaoDTO;
import com.ufpe.aps.produto.Produto;
import com.ufpe.aps.produto.ServicoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("${produto.servlet.path}")
public class ComunicacaoProduto {

    private final ServicoProduto servicoProduto;

    @Autowired
    public ComunicacaoProduto(ServicoProduto servicoProduto) {
        this.servicoProduto = servicoProduto;
    }

    @GetMapping("/{login}")
    public ResponseEntity<List<Produto>> getAllProdutos(@PathVariable String login) {
        return ResponseEntity.ok(servicoProduto.meusProdutos(login));
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Produto> getProdutoById(@PathVariable("id") String id,
//                                                  @RequestParam Integer quantidade)
//            throws ProdutoNotFoundException, QuantidadeProdutoException {
//        if(quantidade == null || quantidade <= 0)
//            throw new QuantidadeProdutoException("Quantidade do produto inválida ou não informada");
//
//        return ResponseEntity.ok(controladorProduto.pegarProduto(id, quantidade));
//    }

    @PostMapping("/publicar")
    public ResponseEntity<Produto> publicarProduto(@RequestBody Produto produto) {
        try{
            servicoProduto.publicarItem(produto);
            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> deletarProduto(@RequestParam String login, @PathVariable("id") String idProduto) throws IsNotOwnerOfProductException {
        if (login == null || login.isEmpty())
            throw new IllegalArgumentException("Login não informado");

        servicoProduto.excluirProduto(login, idProduto);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/avaliar")
    public ResponseEntity<Void> avaliarProduto(AvaliacaoDTO avaliacaoDTO) {
        if (avaliacaoDTO == null || avaliacaoDTO.getLogin().isEmpty() || avaliacaoDTO.getIdProduto().isEmpty() || avaliacaoDTO.getAvaliacao().isEmpty())
            throw new IllegalArgumentException("Login não informado");

        servicoProduto.avaliar(avaliacaoDTO.getLogin(), avaliacaoDTO.getIdProduto(), avaliacaoDTO.getAvaliacao());
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/atualiza-estoque")
//    public ResponseEntity<Void> atualizaEstoque(@RequestBody Carrinho carrinho) {
//        try{
//            servicoProduto.atualizaEstoque(carrinho);
//            return ResponseEntity.noContent().build();
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
}
