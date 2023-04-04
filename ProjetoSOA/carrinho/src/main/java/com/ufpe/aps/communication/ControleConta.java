package com.ufpe.aps.communication;

import com.ufpe.aps.conta.Conta;
import com.ufpe.aps.conta.ControladorConta;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${conta.servlet.path}")
@CrossOrigin
public class ControleConta {

    private final ControladorConta controladorConta;

    @Autowired
    public ControleConta(ControladorConta controladorConta) {
        this.controladorConta = controladorConta;
    }

    @GetMapping
    public ResponseEntity<List<Conta>> getAll() {
        return ResponseEntity.ok().body(controladorConta.getAll());
    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody Conta conta) throws AccountNotFoundException {
        if(conta.getLogin() == null || conta.getSenha() == null)
            throw new IllegalArgumentException("Login ou senha inválidos");

        controladorConta.efetuarLogin(conta);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity efetuarCadastro(@RequestBody Conta conta) throws AccountAlreadyRegisteredException {
        if(conta.getLogin() == null || conta.getSenha() == null)
            throw new IllegalArgumentException("Login ou senha não podem ser nulos");

        controladorConta.cadastrarConta(conta);
        return ResponseEntity.noContent().build();
    }

}
