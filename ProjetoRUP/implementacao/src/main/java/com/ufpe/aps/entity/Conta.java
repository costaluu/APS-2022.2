package com.ufpe.aps.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conta {
    private String login;

    private String senha;

    private Carrinho carrinho;

    public Carrinho pegarCarrinho() {
        return this.carrinho;
    }
}
