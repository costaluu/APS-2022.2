package com.ufpe.aps.repository.impl;

import com.ufpe.aps.entity.Produto;
import com.ufpe.aps.entity.ProdutoParaCarrinho;
import com.ufpe.aps.repository.interfaces.IRepositorioProduto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositorioProdutoBDR implements IRepositorioProduto {

    private List<Produto> produtos;

    private List<ProdutoParaCarrinho> produtosParaCarrinho;

    public RepositorioProdutoBDR() {
        produtos = new ArrayList<>();
    }

    @Override
    public void addAoEstoque(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public Produto pegarProduto(String idProduto) {
        return produtos.stream().filter(p -> p.getId().equals(idProduto)).findFirst().orElse(null);
    }

    @Override
    public void atualizaEstoquesProdutos(Produto produto) {
        produtos.remove(produto);
        produtos.add(produto);
    }
}
