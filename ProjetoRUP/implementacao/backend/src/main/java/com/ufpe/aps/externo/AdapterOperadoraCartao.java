package com.ufpe.aps.externo;

import com.ufpe.aps.entity.Pedido;
import com.ufpe.aps.pojo.PagamentoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.security.auth.login.CredentialNotFoundException;

@Component
public class AdapterOperadoraCartao implements IComunicacaoOperadoraCartao{
    @Override
    public void finalizarPagamento(String login, String numCartao, int codSeguranca, String validade, String nomeNoCartao, Pedido meuPedido) throws Exception {
        try {
            enviarPagamento(login, numCartao, codSeguranca, validade, nomeNoCartao, meuPedido);
        } catch (CredentialNotFoundException e) {
            throw new CredentialNotFoundException("Pagamento não autorizado");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar pagamento");
        }
    }

    private void enviarPagamento(String login, String numCartao, int codSeguranca,
                                 String validade, String nomeNoCartao, Pedido meuPedido) throws Exception{
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8081/pagamento", String.class);

        if(response.getStatusCode().is4xxClientError()){
            throw new CredentialNotFoundException("Pagamento não autorizado");
        } else if(response.getStatusCode().is5xxServerError()){
            throw new RuntimeException("Erro ao enviar pagamento");
        }




//        WebClient.create()
//                .get()
//                .uri("http://localhost:8081/pagamento")
////                .uri("https://63fb89387a045e192b6b0b64.mockapi.io/cartao/pagamento/autorizar")
//                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
//                    throw new RuntimeException("Pagamento não autorizado");
//                })
//                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
//                    throw new RuntimeException("Erro ao enviar pagamento");
//                });
    }


}
