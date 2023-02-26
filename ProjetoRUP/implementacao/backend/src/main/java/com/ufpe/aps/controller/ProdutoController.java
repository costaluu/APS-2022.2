package com.ufpe.aps.controller;

import com.ufpe.aps.repository.interfaces.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("${produto.servlet.path}")
public class ProdutoController {

    @Autowired
    IRepositorioProduto repositorioProduto;

    @GetMapping
    public ResponseEntity getAllProdutos() {
        try{
            return ResponseEntity.ok(repositorioProduto.pegarTodosProdutos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
