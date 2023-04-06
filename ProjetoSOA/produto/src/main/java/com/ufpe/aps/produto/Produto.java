package com.ufpe.aps.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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

    private Float avaliacao;

    @JsonIgnore(JsonInclude.Include.NON_NULL)
    private Integer qtdAvaliacoes;
}
