package com.ufpe.aps.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Conta {
    private String login;

    private String senha;

    private Carrinho carrinho;
}
