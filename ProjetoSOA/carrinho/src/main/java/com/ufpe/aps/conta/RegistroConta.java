package com.ufpe.aps.conta;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.AccountAlreadyRegisteredException;
import com.ufpe.aps.exception.AccountNotFoundException;
import com.ufpe.aps.produto.Produto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerErrorException;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class RegistroConta implements IRegistroConta {

    private final WebClient.Builder wBuilder;

    private final ReactiveCircuitBreaker circuitBreaker;

    private ReactorLoadBalancerExchangeFilterFunction lbFunction;

    @Value("${conta.api.url}")
    private String contaApiUrl;

    @Value("${conta.servlet.path}")
    private String contaServletPath;

    @Value("${conta.api-key}")
    private String apiKey;


    public WebClient client() {
        return wBuilder.baseUrl(contaApiUrl).build();
    }

    public RegistroConta(ReactiveCircuitBreakerFactory cbFactory, WebClient.Builder loadBalancedWebClient, ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        this.circuitBreaker = cbFactory.create("carrinho");
        this.wBuilder = loadBalancedWebClient;
        this.lbFunction = lbFunction;
    }

    @Override
    public Carrinho pegarCarrinho(String login) throws AccountNotFoundException {
        Carrinho carrinho = circuitBreaker.run(
                        client()
                                .get()
                                .uri(uriBuilder ->
                                        uriBuilder.path(contaServletPath + "/carrinho/{id}")
                                                .build(login)
                                )
                                .header("x-api-key", apiKey)
                                .retrieve()
                                .bodyToMono(Carrinho.class), throwable -> {
                            if(throwable instanceof WebClientResponseException.NotFound) {
                                throw new AccountNotFoundException();
                            } else {
                                throw new ServerErrorException("Api de contas n\u00E3o respondeu", throwable);
                            }
                        })
                .block();

        if (carrinho == null)
            throw new AccountNotFoundException();

        return carrinho;
    }

    @Override
    public void atualizarCarrinho(String login, Carrinho carrinho) throws AccountNotFoundException {
        circuitBreaker.run(
                client().post()
                        .uri(uriBuilder ->
                            uriBuilder.path(contaServletPath + "/carrinho/{id}/atualizar")
                                    .build(login)
                        )
                        .body(Mono.just(carrinho), Carrinho.class)
                        .header("x-api-key", apiKey)
                        .retrieve()
                        .bodyToMono(Void.class), throwable -> {
                    if(throwable instanceof WebClientResponseException.NotFound) {
                        throw new AccountNotFoundException();
                    } else {
                        throw new ServerErrorException("Api de contas n\u00E3o respondeu", throwable);
                    }
                }
        ).block();
    }

    // Not used
    @Override
    public void esvaziarCarrinho(String login) throws AccountNotFoundException {
        throw new RuntimeException("Not implemented");
    }

    //Not user
    @Override
    public void efetuarCadastro(Conta conta) throws AccountAlreadyRegisteredException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deletarConta(Conta conta) throws AccountNotFoundException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Conta pegarConta(String login) throws AccountNotFoundException {
        return null;
    }

    @Override
    public List<Conta> getAll() {
        throw new RuntimeException("Not implemented");
    }
}
