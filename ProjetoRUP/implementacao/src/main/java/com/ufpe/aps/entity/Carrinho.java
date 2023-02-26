package com.ufpe.aps.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class Carrinho {

    private Conta conta;

    private List<ProdutoParaCarrinho> produtos;

}
