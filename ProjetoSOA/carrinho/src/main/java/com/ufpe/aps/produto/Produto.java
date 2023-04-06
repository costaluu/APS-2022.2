package com.ufpe.aps.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Integer avaliacao;
}
