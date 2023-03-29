package com.ufpe.aps.carrinho;

import com.ufpe.aps.produtoparacarrinho.ProdutoParaCarrinho;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@Getter
public class Carrinho {

    private Map<String, ProdutoParaCarrinho> produtos;
    public Carrinho(){
        this.produtos = new HashMap<>();
    }


}
