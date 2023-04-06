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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carrinho{");
        for (Map.Entry<String, ProdutoParaCarrinho> entry: produtos.entrySet()) {
            sb.append(entry.getValue().toString());
        }
        sb.append("}");
        return sb.toString();
    }

}
