package com.ufpe.aps.externo;

import com.ufpe.aps.pojo.PagamentoDTO;

public interface IComunicacaoOperadoraCartao {

    public void finalizarPagamento(PagamentoDTO pagamentoDTO);
}
