package com.ufpe.aps.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagamentoDTO {

    private String login;

    private String numCartao;

    private int codSeguranca;

    private String validade;

    private String nomeNoCartao;


}
