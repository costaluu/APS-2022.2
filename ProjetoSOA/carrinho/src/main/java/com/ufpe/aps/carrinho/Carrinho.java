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

    public void esvaziarCarrinho(){
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
