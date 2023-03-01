package com.ufpe.aps.presenter;

import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("${conta.servlet.path}")
public class TelaDeLoginPresenter {

    private final Fachada fachada;

    @Autowired
    public TelaDeLoginPresenter(Fachada fachada) {
        this.fachada = fachada;
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
