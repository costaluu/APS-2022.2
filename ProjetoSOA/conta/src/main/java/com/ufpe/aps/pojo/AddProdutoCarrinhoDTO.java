package com.ufpe.aps.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddProdutoCarrinhoDTO {
    private String login;

    private String idProduto;
    private Integer quantidade;
}
