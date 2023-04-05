package com.ufpe.aps.produto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    private String id;

    private String dono;

    private String nome;

    private String descricao;

    private Integer totalUnidades;

    private Double valor;

    private String avaliacao;
}
