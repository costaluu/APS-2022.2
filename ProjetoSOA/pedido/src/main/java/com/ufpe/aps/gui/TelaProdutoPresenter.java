package com.ufpe.aps.gui;

import com.ufpe.aps.pojo.AddProdutoCarrinhoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("${carrinho.servlet.path}")
public class TelaProdutoPresenter {

    private final Fachada fachada;

    @Autowired
    public TelaProdutoPresenter(Fachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping("/adicionar")
    public ResponseEntity addProdutoCarrinho(@RequestBody AddProdutoCarrinhoDTO produtoDTO) {
        try{
            fachada.addProdutoCarrinho(produtoDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
