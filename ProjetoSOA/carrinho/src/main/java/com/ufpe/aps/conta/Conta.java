package com.ufpe.aps.conta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ufpe.aps.carrinho.Carrinho;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String senha;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Carrinho carrinho;
}
