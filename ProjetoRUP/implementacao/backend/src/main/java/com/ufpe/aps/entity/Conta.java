package com.ufpe.aps.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "conta")
public class Conta {
    @Id
    private String login;

    private String senha;

    private Carrinho carrinho;

    public Carrinho pegarCarrinho() {
        return this.carrinho;
    }
}
