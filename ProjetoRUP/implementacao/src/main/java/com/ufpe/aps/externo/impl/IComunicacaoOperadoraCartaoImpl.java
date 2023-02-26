package com.ufpe.aps.externo.impl;

import com.ufpe.aps.externo.IComunicacaoOperadoraCartao;
import com.ufpe.aps.pojo.PagamentoDTO;
import org.springframework.stereotype.Component;

@Component
public class IComunicacaoOperadoraCartaoImpl implements IComunicacaoOperadoraCartao {

    @Override
    public void finalizarPagamento(PagamentoDTO pagamentoDTO) {
    }
}
