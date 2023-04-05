package com.ufpe.aps.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Document(collection = "produto")
public class Produto {
    private String id;

    private String dono;

    private String nome;

    private String descricao;

    private Integer totalUnidades;

    private Double valor;

    @Override
    public String toString() {
        return "Produto{" +
                "id='" + id + '\'' +
                ", dono='" + dono + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", totalUnidades=" + totalUnidades +
                ", valor=" + valor +
                '}';
    }
}
