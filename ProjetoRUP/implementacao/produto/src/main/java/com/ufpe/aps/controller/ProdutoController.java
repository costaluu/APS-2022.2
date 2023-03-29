package com.ufpe.aps.controller;

import com.ufpe.aps.produto.IRepositorioProduto;
import com.ufpe.aps.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin
@RequestMapping("${produto.servlet.path}")
public class ProdutoController {

    private final IRepositorioProduto repositorioProduto;

    @Autowired
    public ProdutoController(IRepositorioProduto repositorioProduto) {
        this.repositorioProduto = repositorioProduto;
    }

    @GetMapping
    public ResponseEntity getAllProdutos() {
        try{
            return ResponseEntity.ok(repositorioProduto.pegarTodosProdutos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("id") String id, @RequestParam Integer quantidade) {
        return ResponseEntity.ok(repositorioProduto.pegarProduto(id, quantidade));
    }
}
