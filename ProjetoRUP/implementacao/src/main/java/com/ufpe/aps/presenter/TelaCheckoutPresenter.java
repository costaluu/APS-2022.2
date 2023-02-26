package com.ufpe.aps.presenter;

import com.ufpe.aps.fachada.Fachada;
import com.ufpe.aps.pojo.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("${conta.servlet.path}")
public class TelaCheckoutPresenter {

    @Autowired
    Fachada fachada;

    @PostMapping("/pagamento")
    public ResponseEntity efetuarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        try{
            fachada.realizarPagamento(pagamentoDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), null, 500);
        }
    }
}
