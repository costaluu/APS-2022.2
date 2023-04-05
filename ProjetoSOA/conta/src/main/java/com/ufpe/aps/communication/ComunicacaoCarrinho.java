package com.ufpe.aps.communication;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.conta.IRegistroConta;
import com.ufpe.aps.exception.AccountNotFoundException;
import com.ufpe.aps.pojo.AddProdutoCarrinhoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/conta-carrinho")
public class ComunicacaoCarrinho {

    private final IRegistroConta registroConta;

    public ComunicacaoCarrinho(IRegistroConta registroConta) {
        this.registroConta = registroConta;
    }

    @GetMapping("/{login}")
    public ResponseEntity<Carrinho> pegarCarrinho(@PathVariable String login) throws AccountNotFoundException {
        if(login == null || login.isEmpty())
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(registroConta.pegarCarrinho(login));
    }

    @PostMapping("/{login}/atualizar")
    public ResponseEntity<Void> atualizarCarrinho(@PathVariable String login, @RequestBody Carrinho carrinho) throws AccountNotFoundException {
        if(carrinho == null || login == null || login.isEmpty())
            return ResponseEntity.badRequest().build();

        registroConta.atualizarCarrinho(login, carrinho);
        return ResponseEntity.noContent().build();
    }
}
