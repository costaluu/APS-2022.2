package com.ufpe.aps.produto;

import com.ufpe.aps.carrinho.Carrinho;
import org.springframework.stereotype.Component;

@Component
public class CadastroProduto {

    private final IRepositorioProduto repositorioProduto;

    public CadastroProduto(IRepositorioProduto repositorioProduto) {
        this.repositorioProduto = repositorioProduto;
    }

    public Produto pegarProduto(String idProduto, Integer quantidade) {
        return repositorioProduto.pegarProduto(idProduto, quantidade);
    }

    public void addAoEstoque(Produto produto) {
        repositorioProduto.addAoEstoque(produto);
    }

    public void atualizaEstoquesProdutos(Carrinho carrinho) {
        repositorioProduto.atualizaEstoquesProdutos(carrinho);
    }
}
