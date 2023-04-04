package com.ufpe.aps.communication;

import com.ufpe.aps.conta.RegistroConta;
import com.ufpe.aps.conta.ServicoConta;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${conta.servlet.path}")
@CrossOrigin
public class FachadaConta {

    private final ServicoConta servicoConta;

    @Autowired
    public FachadaConta(ServicoConta servicoConta) {
        this.servicoConta = servicoConta;
    }

    @GetMapping
    public ResponseEntity<List<RegistroConta>> getAll() {
        return ResponseEntity.ok().body(servicoConta.getAll());
    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody RegistroConta registroConta) throws AccountNotFoundException {
        if(registroConta.getLogin() == null || registroConta.getSenha() == null)
            throw new IllegalArgumentException("Login ou senha inválidos");

        servicoConta.efetuarLogin(registroConta);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity efetuarCadastro(@RequestBody RegistroConta registroConta) throws AccountAlreadyRegisteredException {
        if(registroConta.getLogin() == null || registroConta.getSenha() == null)
            throw new IllegalArgumentException("Login ou senha não podem ser nulos");

        servicoConta.efetuarCadastro(registroConta);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deletar")
    public ResponseEntity deletarConta(@RequestBody RegistroConta registroConta) {
        if(registroConta.getLogin() == null || registroConta.getSenha() == null)
            throw new IllegalArgumentException("Login ou senha não podem ser nulos");

        servicoConta.deletarConta(registroConta);
        return ResponseEntity.noContent().build();
    }

}
