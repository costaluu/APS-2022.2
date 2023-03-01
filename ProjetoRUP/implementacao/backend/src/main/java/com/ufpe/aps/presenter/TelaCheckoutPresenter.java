package com.ufpe.aps.presenter;

import com.ufpe.aps.fachada.Fachada;
import com.ufpe.aps.pojo.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.security.auth.login.CredentialNotFoundException;

@RestController
@CrossOrigin
@RequestMapping("${carrinho.servlet.path}")
public class TelaCheckoutPresenter {

    private final Fachada fachada;

    @Autowired
    public TelaCheckoutPresenter(Fachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping("/pagamento")
    public ResponseEntity efetuarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        try{
            fachada.realizarPagamento(pagamentoDTO);
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }
}
