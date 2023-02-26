package com.ufpe.aps.presenter;

import com.ufpe.aps.fachada.Fachada;
import com.ufpe.aps.pojo.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("${conta.servlet.path}")
public class TelaCheckoutPresenter {

    @Autowired
    Fachada fachada;

    @PostMapping("/pagamento")
    public void efetuarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        fachada.realizarPagamento(pagamentoDTO);
    }
}
