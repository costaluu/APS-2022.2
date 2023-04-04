package com.ufpe.aps.sistemaoperadoracartao.sdk;

import com.ufpe.aps.pedido.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class SDKOperadoraCartao {

    private static String urlApiCartao;

    private static final RestTemplate restTemplate = new RestTemplate();

    public SDKOperadoraCartao(Environment env) {
        this.urlApiCartao = env.getProperty("url.api.cartao");
    }

    public void realizarPagamento(PagamentoCartaoDTO pagamentoCartaoDTO){

            ResponseEntity<String> response = restTemplate.getForEntity(urlApiCartao, String.class);

            if(response.getStatusCode().is4xxClientError()){
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Pagamento não autorizado");
            } else if(response.getStatusCode().is5xxServerError()){
                throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao enviar pagamento");
            }
    }
}
