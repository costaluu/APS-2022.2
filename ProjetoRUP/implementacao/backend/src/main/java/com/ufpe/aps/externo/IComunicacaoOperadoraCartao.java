package com.ufpe.aps.externo;

import com.ufpe.aps.entity.Pedido;
import com.ufpe.aps.pojo.PagamentoDTO;
import org.springframework.web.client.HttpClientErrorException;

import javax.security.auth.login.CredentialNotFoundException;

public interface IComunicacaoOperadoraCartao {

    public void finalizarPagamento(String login, String numCartao, int codSeguranca,
                                   String validade, String nomeNoCartao, Pedido meuPedido) throws HttpClientErrorException;
}
