package com.ufpe.aps.conta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ufpe.aps.carrinho.Carrinho;
import lombok.*;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String senha;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Carrinho carrinho;

    public Carrinho pegarCarrinho() {
        return this.carrinho;
    }
}
