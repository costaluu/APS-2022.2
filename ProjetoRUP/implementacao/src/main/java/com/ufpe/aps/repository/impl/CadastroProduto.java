package com.ufpe.aps.repository.impl;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Produto;
import com.ufpe.aps.entity.ProdutoParaCarrinho;
import com.ufpe.aps.repository.interfaces.IRepositorioProduto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CadastroProduto implements IRepositorioProduto {

    private Map<String, Produto> produtos;

    public CadastroProduto() {
        produtos = new HashMap<>();
        produtos.put("1", new Produto("1", "Teste1", "Descrição 1" , "Categoria 1", 10, 10.0));
        produtos.put("2", new Produto("2", "Teste1", "Descrição 2" , "Categoria 2", 10, 10.0));
        produtos.put("3", new Produto("3", "Teste1", "Descrição 3" , "Categoria 3", 10, 10.0));
        produtos.put("4", new Produto("4", "Teste1", "Descrição 4" , "Categoria 4", 10, 10.0));
        produtos.put("5", new Produto("5", "Teste1", "Descrição 5" , "Categoria 5", 10, 10.0));
        produtos.put("6", new Produto("6", "Teste1", "Descrição 6" , "Categoria 6", 10, 10.0));
        produtos.put("7", new Produto("7", "Teste1", "Descrição 7" , "Categoria 7", 10, 10.0));
        produtos.put("8", new Produto("8", "Teste1", "Descrição 8" , "Categoria 8", 10, 10.0));
        produtos.put("9", new Produto("9", "Teste1", "Descrição 9" , "Categoria 9", 10, 10.0));
        produtos.put("10", new Produto("10", "Teste1", "Descrição 10" , "Categoria 10", 10, 10.0));
    }

    @Override
    public void addAoEstoque(Produto produto) {
        produtos.put(produto.getId(), produto);
    }

    @Override
    public Produto pegarProduto(String idProduto, Integer quantidade) {
        if(produtos.get(idProduto).getTotalUnidades() < quantidade)
            return null;
        return produtos.get(idProduto);
    }

    @Override
    public void atualizaEstoquesProdutos(Carrinho carrinho) {
        for(ProdutoParaCarrinho elem : carrinho.getProdutos().values()){
            Produto produto = produtos.get(elem.getProduto().getId());
            produto.setTotalUnidades(produto.getTotalUnidades() - elem.getQuantidade());
            produtos.remove(produto.getId());
            produtos.put(produto.getId(), produto);
        }
    }

    @Override
    public List<Produto> pegarTodosProdutos() {
        return new ArrayList<>(produtos.values());
    }
}
