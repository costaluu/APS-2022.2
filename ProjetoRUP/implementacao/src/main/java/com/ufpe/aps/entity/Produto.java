package com.ufpe.aps.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
public class Produto {
    @JsonProperty(required = true)
    private String id;

    @JsonProperty(required = true)
    private String dono;

    @JsonProperty(required = true)
    private String nome;

    @JsonProperty(required = true)
    private String descricao;

    @JsonProperty(required = true)
    private Integer totalUnidades;

    @JsonProperty(required = true)
    private Double valor;
}
