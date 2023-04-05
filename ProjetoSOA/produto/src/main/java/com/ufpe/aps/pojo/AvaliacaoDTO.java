package com.ufpe.aps.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO {

    private String avaliacao;
    private String idProduto;

    private String login;
}
