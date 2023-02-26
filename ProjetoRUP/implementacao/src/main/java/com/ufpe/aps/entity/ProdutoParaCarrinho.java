package com.ufpe.aps.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class ProdutoParaCarrinho {

    private Integer id;

    private Integer quantidade;
}
