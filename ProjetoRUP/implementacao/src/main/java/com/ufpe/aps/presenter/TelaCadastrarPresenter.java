package com.ufpe.aps.presenter;

import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("${conta.servlet.path}")
public class TelaCadastrarPresenter {

    @Autowired
    Fachada fachada;

    @PostMapping("/cadastrar")
    public void efetuarCadastro(@RequestBody Conta conta) {
        if(conta != null) {
            if (conta.getLogin() == null || conta.getSenha() == null)
                return;
            fachada.cadastrarConta(conta.getLogin(), conta.getSenha());
        }
    }
}
