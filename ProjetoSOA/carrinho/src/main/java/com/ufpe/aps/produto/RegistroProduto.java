package com.ufpe.aps.produto;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.ProdutoNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerErrorException;

import java.util.List;

@Component
public class RegistroProduto implements IRegistroProduto {

    private final WebClient.Builder wBuilder;

    private final ReactiveCircuitBreaker circuitBreaker;

    @Value("${produto.api.url}")
    private String produtoApiUrl;

    public RegistroProduto(WebClient.Builder wBuilder, ReactiveCircuitBreakerFactory cbFactory) {
        this.wBuilder = wBuilder;
        this.circuitBreaker = cbFactory.create("produto");
    }

    private WebClient client() {
        return wBuilder.baseUrl(produtoApiUrl).build();
    }


    @Override
    public Produto pegarProduto(String idProduto, int quantidade) {
        return circuitBreaker.run(
                        client().get()
                                .uri(uriBuilder ->
                                        uriBuilder.path("/produto/{id}")
                                                .queryParam("quantidade", quantidade)
                                                .build(idProduto)
                                )
                                .retrieve()
                                .bodyToMono(Produto.class), throwable -> {
                            if(throwable instanceof WebClientResponseException.NotFound)
                                throw new ProdutoNotFoundException("Produto não encontrado", throwable);
                            throw new ServerErrorException("Api de produtos não respondeu", throwable);
                        })
                .block();
    }

    @Override
    public void atualizarEstoque(Carrinho carrinho) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public void criarProduto(String login, Produto produto, int quantidade) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deletarProduto(String idProduto) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void atualizarAvaliacao(String idProduto, String avaliacao) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<Produto> pegarTodosProdutos(String login) {
        throw new RuntimeException("Not implemented");
    }
}
