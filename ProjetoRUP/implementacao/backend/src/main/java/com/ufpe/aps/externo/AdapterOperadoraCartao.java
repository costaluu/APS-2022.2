package com.ufpe.aps.externo;

import com.ufpe.aps.entity.Pedido;
import com.ufpe.aps.pojo.PagamentoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AdapterOperadoraCartao implements IComunicacaoOperadoraCartao{
    @Override
    public void finalizarPagamento(String login, String numCartao, int codSeguranca, String validade, String nomeNoCartao, Pedido meuPedido) throws Exception {
        try {
            enviarPagamento(login, numCartao, codSeguranca, validade, nomeNoCartao, meuPedido);
        } catch (Exception e) {
            throw new Exception("Erro ao enviar pagamento");
        }
    }

    private void enviarPagamento(String login, String numCartao, int codSeguranca, String validade, String nomeNoCartao, Pedido meuPedido){
        WebClient.create()
                .get()
                .uri("https://63fb89387a045e192b6b0b64.mockapi.io/cartao/pagamento/autorizar")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    throw new RuntimeException("Pagamento não autorizado");
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    throw new RuntimeException("Erro ao enviar pagamento");
                });
    }


}
