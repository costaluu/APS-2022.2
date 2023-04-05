package com.ufpe.aps.conta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.converter.ContaCarrinhoConverter;
//import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import org.hibernate.type.SqlTypes;
//import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "conta")
//@Document(collection = "conta")
public class Conta {
    @Id
    private String login;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String senha;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Convert(converter = ContaCarrinhoConverter.class)
    @JdbcTypeCode(SqlTypes.JSON)
    private Carrinho carrinho;
}
