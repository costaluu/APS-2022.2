package com.ufpe.aps.communication;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.conta.Conta;
import com.ufpe.aps.conta.FachadaConta;
import com.ufpe.aps.conta.IRepositorioConta;
import com.ufpe.aps.conta.IServicoConta;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;
import com.ufpe.aps.repository.bdr.ContaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${conta.servlet.path}")
public class ComunicacaoConta {

    private final FachadaConta fachadaConta;

    @Value("${conta.api-key}")
    private String apiKey;

    @Autowired
    public ComunicacaoConta(Environment env, ContaDAO repository) {
        this.fachadaConta = new FachadaConta(env, repository);
    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody Conta conta) throws AccountNotFoundException {
        if(conta.getLogin() == null || conta.getSenha() == null)
            throw new IllegalArgumentException("Login ou senha inválidos");

        fachadaConta.efetuarLogin(conta);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity efetuarCadastro(@RequestBody Conta conta) throws AccountAlreadyRegisteredException {
        if(conta.getLogin() == null || conta.getSenha() == null)
            throw new IllegalArgumentException("Login ou senha não podem ser nulos");

        fachadaConta.efetuarCadastro(conta);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deletar")
    public ResponseEntity deletarConta(@RequestBody Conta conta) throws AccountNotFoundException {
        if(conta.getLogin() == null || conta.getSenha() == null)
            throw new IllegalArgumentException("Login ou senha não podem ser nulos");

        fachadaConta.deletarConta(conta);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Conta>> pegarConta() {
        return ResponseEntity.ok().body(fachadaConta.getAll());
    }

    @GetMapping("/carrinho/{login}")
    public ResponseEntity<Carrinho> pegarCarrinho(@RequestHeader("X-API-KEY") String apiKey, @PathVariable String login) throws AccountNotFoundException {
        if(login == null || login.isEmpty())
            return ResponseEntity.badRequest().build();

        if(apiKey == null || apiKey.isEmpty())
            throw new IllegalArgumentException("API KEY não informada");

        if(!apiKey.equals(this.apiKey))
            throw new IllegalArgumentException("API KEY inválida");

        return ResponseEntity.ok().body(fachadaConta.pegarCarrinho(login));
    }

    @PostMapping("/carrinho/{login}/atualizar")
    public ResponseEntity<Void> atualizarCarrinho(@RequestHeader("X-API-KEY") String apiKey, @PathVariable String login, @RequestBody Carrinho carrinho) throws AccountNotFoundException {
        if(carrinho == null || login == null || login.isEmpty())
            return ResponseEntity.badRequest().build();

        if(apiKey == null || apiKey.isEmpty())
            throw new IllegalArgumentException("API KEY não informada");

        if(!apiKey.equals(this.apiKey))
            throw new IllegalArgumentException("API KEY inválida");

        fachadaConta.atualizarCarrinho(login, carrinho);
        return ResponseEntity.noContent().build();
    }

}
