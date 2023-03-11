package com.ufpe.aps.sistemaoperadoracartao;

import com.ufpe.aps.pedido.Pedido;
import org.springframework.web.client.HttpClientErrorException;

public interface IComunicacaoOperadoraCartao {

    public void finalizarPagamento(String login, String numCartao, int codSeguranca,
                                   String validade, String nomeNoCartao, Pedido meuPedido) throws HttpClientErrorException;
}
