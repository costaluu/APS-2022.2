package com.ufpe.aps.communication;

import com.ufpe.aps.conta.Conta;
import com.ufpe.aps.conta.IRepositorioConta;
import com.ufpe.aps.conta.IServicoConta;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${conta.servlet.path}")
@CrossOrigin
public class ComunicacaoConta {

    private final IServicoConta servicoConta;

    private IRepositorioConta repositorioConta;

    @Autowired
    public ComunicacaoConta(IServicoConta servicoConta) {
        this.servicoConta = servicoConta;
    }

//    @GetMapping
//    public ResponseEntity<List<Conta>> getAll() {
//        return ResponseEntity.ok().body(servicoConta.getAll());
//    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody Conta conta) throws AccountNotFoundException {
        if(conta.getLogin() == null || conta.getSenha() == null)
            throw new IllegalArgumentException("Login ou senha inválidos");

        servicoConta.efetuarLogin(conta);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity efetuarCadastro(@RequestBody Conta conta) throws AccountAlreadyRegisteredException {
        if(conta.getLogin() == null || conta.getSenha() == null)
            throw new IllegalArgumentException("Login ou senha não podem ser nulos");

        servicoConta.efetuarCadastro(conta);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deletar")
    public ResponseEntity deletarConta(@RequestBody Conta conta) throws AccountNotFoundException {
        if(conta.getLogin() == null || conta.getSenha() == null)
            throw new IllegalArgumentException("Login ou senha não podem ser nulos");

        servicoConta.deletarConta(conta);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Conta>> pegarConta() {
        return ResponseEntity.ok().body(servicoConta.getAll());
    }

}
