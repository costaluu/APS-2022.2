package com.ufpe.aps.produto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerErrorException;

@Component
public class ControleProduto {

    private final WebClient.Builder wBuilder;

    private final ReactiveCircuitBreaker circuitBreaker;

    @Value("${produto.api.url:http://localhost:8084}")
    private String produtoApiUrl;

    private WebClient client() {
        return wBuilder.baseUrl(produtoApiUrl).build();
    }

    public ControleProduto(ReactiveCircuitBreakerFactory cbFactory, WebClient.Builder wBuilder) {
        this.circuitBreaker = cbFactory.create("produto");
        this.wBuilder = wBuilder;
    }

    public Produto pegarProduto(String idProduto, Integer quantidade) {
        return circuitBreaker.run(
                    client().get()
                    .uri(uriBuilder ->
                      uriBuilder.path("/produto/{id}")
                              .queryParam("quantidade", quantidade)
                              .build(idProduto)
                    )
                    .retrieve()
                    .bodyToMono(Produto.class), throwable -> {
                        throw new ServerErrorException("Erro ao pegar produto", throwable);
                    })
                .block();
    }
}
