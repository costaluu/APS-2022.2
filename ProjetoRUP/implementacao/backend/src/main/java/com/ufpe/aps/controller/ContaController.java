package com.ufpe.aps.controller;

import com.ufpe.aps.repository.IRepositorioConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("${conta.servlet.path}")
public class ContaController {


    private final IRepositorioConta repositorioConta;

    @Autowired
    public ContaController(IRepositorioConta repositorioConta) {
        this.repositorioConta = repositorioConta;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(repositorioConta.getAll());
    }
}
