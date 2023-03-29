package com.ufpe.aps.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerErrorException;

import java.util.Map;

@Component
public class CadastroProduto {

    @Autowired
    WebClient.Builder wBuilder;

    private ReactiveCircuitBreaker circuitBreaker;

    private WebClient client() {
        return wBuilder.baseUrl("http://produto:8084").build();
    }

    public CadastroProduto(ReactiveCircuitBreakerFactory cbFactory) {
        this.circuitBreaker = cbFactory.create("produto");
    }

    public Produto pegarProduto(String idProduto, Integer quantidade) {
        Produto produto = circuitBreaker.run(
                    client().get()
                    .uri(uriBuilder ->
                      uriBuilder.path("/produto/{id}")
                              .queryParam("quantidade", quantidade)
                              .build(idProduto)
                    )
                    .retrieve()
                    .bodyToMono(Produto.class), throwable -> {
                        throw new ServerErrorException("Api de produtos não respondeu");
                    })
                .block();
        return produto;
    }
}
