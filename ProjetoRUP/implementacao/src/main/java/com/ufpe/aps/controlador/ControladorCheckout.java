package com.ufpe.aps.controlador;

import com.ufpe.aps.externo.IComunicacaoOperadoraCartao;
import com.ufpe.aps.pojo.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorCheckout {

    @Autowired
    IComunicacaoOperadoraCartao comunicacaoOperadoraCartao;

    public void realizarPagamento(PagamentoDTO pagamentoDTO) throws Exception {
        comunicacaoOperadoraCartao.finalizarPagamento(pagamentoDTO);
    }
}
