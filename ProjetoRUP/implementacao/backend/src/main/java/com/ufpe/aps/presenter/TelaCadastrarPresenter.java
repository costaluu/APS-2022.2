package com.ufpe.aps.presenter;

import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("${conta.servlet.path}")
public class TelaCadastrarPresenter {


    private final Fachada fachada;

    @Autowired
    public TelaCadastrarPresenter(Fachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity efetuarCadastro(@RequestBody Conta conta) {
        if(conta != null) {
            if (conta.getLogin() == null || conta.getSenha() == null)
                return ResponseEntity.badRequest().build();
            try{
                fachada.cadastrarConta(conta.getLogin(), conta.getSenha());
                return ResponseEntity.ok().build();
            } catch (AccountAlreadyRegisteredException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
