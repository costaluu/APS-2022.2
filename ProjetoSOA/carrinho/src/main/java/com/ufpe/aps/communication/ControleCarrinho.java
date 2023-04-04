package com.ufpe.aps.communication;

import com.ufpe.aps.carrinho.ControladorCarrinho;
import com.ufpe.aps.exception.AccountNotFoundException;
import com.ufpe.aps.pojo.AddProdutoCarrinhoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${carrinho.servlet.path}")
public class ControleCarrinho {

    private final ControladorCarrinho controladorCarrinho;

    public ControleCarrinho(ControladorCarrinho controladorCarrinho) {
        this.controladorCarrinho = controladorCarrinho;
    }

    @PostMapping("/adicionar")
    public ResponseEntity addProdutoCarrinho(@RequestBody AddProdutoCarrinhoDTO produtoDTO) throws AccountNotFoundException {
        controladorCarrinho.addProdutoCarrinho(produtoDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{contaId}")
    public ResponseEntity getProdutosCarrinho(@PathVariable("contaId") String contaId) throws AccountNotFoundException {
        return ResponseEntity.ok().body(controladorCarrinho.getProdutosCarrinho(contaId));
    }
}
