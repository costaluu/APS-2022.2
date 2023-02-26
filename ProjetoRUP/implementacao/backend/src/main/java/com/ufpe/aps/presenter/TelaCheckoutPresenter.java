package com.ufpe.aps.presenter;

import com.ufpe.aps.fachada.Fachada;
import com.ufpe.aps.pojo.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialNotFoundException;

@RestController
@CrossOrigin
@RequestMapping("${carrinho.servlet.path}")
public class TelaCheckoutPresenter {

    @Autowired
    Fachada fachada;

    @PostMapping("/pagamento")
    public ResponseEntity efetuarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        try{
            fachada.realizarPagamento(pagamentoDTO);
            return ResponseEntity.ok().build();
        } catch (CredentialNotFoundException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), null, 500);
        }
    }
}
