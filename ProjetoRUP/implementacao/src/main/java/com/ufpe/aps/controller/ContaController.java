package com.ufpe.aps.controller;

import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("${conta.servlet.path}")
public class ContaController {
    @Autowired
    Fachada fachada;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarConta(@RequestBody Conta conta) {
        if(conta != null) {
            if (conta.getLogin() == null || conta.getSenha() == null)
                return ResponseEntity.badRequest().build();

            fachada.cadastrarConta(conta.getLogin(), conta.getSenha());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody Conta conta) {
        if(conta != null) {
            if (conta.getLogin() == null || conta.getSenha() == null)
                return ResponseEntity.badRequest().build();
            boolean login = fachada.efetuarLogin(conta.getLogin(), conta.getSenha());
            if(login)
                return ResponseEntity.ok().build();
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
