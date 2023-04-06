package com.ufpe.aps.communication;

import com.ufpe.aps.carrinho.FachadaCarrinho;
import com.ufpe.aps.exception.AccountNotFoundException;
import com.ufpe.aps.pojo.AddProdutoCarrinhoDTO;
import com.ufpe.aps.pojo.DeleteProdutoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${carrinho.servlet.path}")
public class ComunicacaoCarrinho {

    private final FachadaCarrinho fachadaCarrinho;

    public ComunicacaoCarrinho(FachadaCarrinho fachadaCarrinho) {
        this.fachadaCarrinho = fachadaCarrinho;
    }

    @PostMapping("/adicionar")
    public ResponseEntity addProdutoCarrinho(@RequestBody AddProdutoCarrinhoDTO produtoDTO) throws AccountNotFoundException {
        fachadaCarrinho.addProdutoCarrinho(produtoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remover")
    public ResponseEntity removeProdutoCarrinho(@RequestBody DeleteProdutoDTO deleteProdutoDTO) throws AccountNotFoundException {
        fachadaCarrinho.removerProdutoCarrinho(deleteProdutoDTO.getLogin(), deleteProdutoDTO.getIdProduto());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity atualizarProdutoCarrinho(@RequestBody AddProdutoCarrinhoDTO produtoDTO) throws AccountNotFoundException {
        fachadaCarrinho.atualizarProdutoCarrinho(produtoDTO.getLogin(), produtoDTO.getIdProduto(), produtoDTO.getQuantidade());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{login}")
    public ResponseEntity getCarrinho(@PathVariable String login) throws AccountNotFoundException {
        return ResponseEntity.ok(fachadaCarrinho.pegarCarrinho(login));
    }
}
