package com.ufpe.aps.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Getter
public class Carrinho {

//    private List<ProdutoParaCarrinho> produtos;
    private Map<String, ProdutoParaCarrinho> produtos;
    public Carrinho(){
        this.produtos = new HashMap<>();
    }

    public void esvaziarCarrinho(){
//        this.produtos = new ArrayList<>();
        this.produtos.clear();
    }

    public void addProduto(ProdutoParaCarrinho produto){
        if(this.produtos.containsKey(produto.getProduto().getId())){
            ProdutoParaCarrinho produtoParaCarrinho = this.produtos.get(produto.getProduto().getId());
            produtoParaCarrinho.setQuantidade(produtoParaCarrinho.getQuantidade() + produto.getQuantidade());
            this.produtos.remove(produto.getProduto().getId());
            this.produtos.put(produto.getProduto().getId(), produtoParaCarrinho);
        }else {
            this.produtos.put(produto.getProduto().getId(), produto);
        }
    }

}
