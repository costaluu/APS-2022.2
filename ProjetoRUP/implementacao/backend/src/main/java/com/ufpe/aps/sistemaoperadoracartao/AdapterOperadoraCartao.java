package com.ufpe.aps.sistemaoperadoracartao;

import com.ufpe.aps.pedido.Pedido;
import com.ufpe.aps.sistemaoperadoracartao.sdk.PagamentoCartaoDTO;
import com.ufpe.aps.sistemaoperadoracartao.sdk.SDKOperadoraCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AdapterOperadoraCartao implements IComunicacaoOperadoraCartao{

    private final SDKOperadoraCartao SDKOperadoraCartao;

    @Autowired
    public AdapterOperadoraCartao(SDKOperadoraCartao sdkOperadoraCartao) {
        this.SDKOperadoraCartao = sdkOperadoraCartao;
    }

    @Override
    public void finalizarPagamento(String login, String numCartao, int codSeguranca,
                                   String validade, String nomeNoCartao, Pedido meuPedido)
            throws HttpClientErrorException {
        PagamentoCartaoDTO pagamentoCartaoDTO = new PagamentoCartaoDTO(login, numCartao, codSeguranca, validade, nomeNoCartao, meuPedido);
        SDKOperadoraCartao.realizarPagamento(pagamentoCartaoDTO);
    }

}
