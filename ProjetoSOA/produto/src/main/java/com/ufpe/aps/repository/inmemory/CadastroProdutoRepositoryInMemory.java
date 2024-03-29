package com.ufpe.aps.repository.inmemory;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.produto.Produto;
import com.ufpe.aps.produtoparacarrinho.ProdutoParaCarrinho;
import com.ufpe.aps.produto.IRepositorioProduto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CadastroProdutoRepositoryInMemory implements IRepositorioProduto {

    private final List<Produto> produtos;

    private static CadastroProdutoRepositoryInMemory instance;

    public static synchronized CadastroProdutoRepositoryInMemory getInstance() {
        if (instance == null) {
            instance = new CadastroProdutoRepositoryInMemory();
        }
        return instance;
    }

    public CadastroProdutoRepositoryInMemory() {
        produtos = new ArrayList<>();
        produtos.add(new Produto("1", "Teste1", "Descrição 1" , "Categoria 1", 10, 10.0, null, 0));
        produtos.add(new Produto("2", "Teste1", "Descrição 2" , "Categoria 2", 10, 10.0, null, 0));
        produtos.add(new Produto("3", "Teste1", "Descrição 3" , "Categoria 3", 10, 10.0, null, 0));
        produtos.add(new Produto("4", "Teste1", "Descrição 4" , "Categoria 4", 10, 10.0, null, 0));
        produtos.add(new Produto("5", "Teste1", "Descrição 5" , "Categoria 5", 10, 10.0, null, 0));
        produtos.add(new Produto("6", "Teste1", "Descrição 6" , "Categoria 6", 10, 10.0, null, 0));
        produtos.add(new Produto("7", "Teste1", "Descrição 7" , "Categoria 7", 10, 10.0, null, 0));
        produtos.add(new Produto("8", "Teste1", "Descrição 8" , "Categoria 8", 10, 10.0, null, 0));
        produtos.add(new Produto("9", "Teste1", "Descrição 9" , "Categoria 9", 10, 10.0, null, 0));
        produtos.add(new Produto("10", "Teste1", "Descrição 10" , "Categoria 10", 10, 10.0, null, 0));
        System.out.println("Produtos criados: " + produtos.size());
    }

    @Override
    public void addAoEstoque(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public Produto pegarProduto(String idProduto, Integer quantidade) {
        return produtos.stream().filter(p -> p.getId().equals(idProduto) && p.getTotalUnidades() >= quantidade).findFirst().orElse(null);
    }

    @Override
    public Produto pegarProduto(String idProduto) {
        return produtos.stream().filter(p -> p.getId().equals(idProduto)).findFirst().orElse(null);
    }

    @Override
    public void atualizaEstoquesProdutos(Carrinho carrinho) {
        for(ProdutoParaCarrinho elem : carrinho.getProdutos().values()){
            Produto produto = pegarProduto(elem.getProduto().getId());
            produto.setTotalUnidades(produto.getTotalUnidades() - elem.getQuantidade());
            produtos.stream().filter(p -> p.getId().equals(produto.getId()))
                    .findFirst()
                    .ifPresent(p -> p.setTotalUnidades(p.getTotalUnidades() - produto.getTotalUnidades()));
        }
    }

    @Override
    public List<Produto> pegarTodosProdutos() {
        return List.copyOf(produtos);
    }

    @Override
    public List<Produto> pegarMeusProdutos(String login) {
        return produtos.stream().filter(p -> p.getDono().equals(login)).collect(Collectors.toList());
    }

    @Override
    public void deletarProduto(String idProduto) {
        produtos.removeIf(p -> p.getId().equals(idProduto));
    }

    @Override
    public void atualizarAvaliacao(String idProduto, Float avaliacao) {
        produtos.stream().filter(p -> p.getId().equals(idProduto))
                .findFirst()
                .ifPresent(p -> p.setAvaliacao(avaliacao));
    }
}
