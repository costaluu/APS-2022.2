package com.ufpe.aps.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private String id;

    private String dono;

    private String nome;

    private String descricao;

    private Integer totalUnidades;

    private Double valor;
}