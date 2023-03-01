package com.ufpe.aps.repository.mongo;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Produto;
import com.ufpe.aps.entity.ProdutoParaCarrinho;
import com.ufpe.aps.repository.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CadastroProdutoMongo implements IRepositorioProduto {

    private final ProdutoMongoRepository produtoMongoRepository;

    private static int count = 0;

    @Autowired
    public CadastroProdutoMongo(ProdutoMongoRepository produtoMongoRepository) {
        this.produtoMongoRepository = produtoMongoRepository;
        System.out.println("Criando CadastroProdutoMongo: " + count++);
    }

    @Override
    public void addAoEstoque(Produto produto) {
        produtoMongoRepository.save(produto);
    }

    @Override
    public Produto pegarProduto(String idProduto, Integer quantidade) {
        return produtoMongoRepository.findById(idProduto).orElse(null);
    }

    @Override
    public void atualizaEstoquesProdutos(Carrinho carrinho) {
        for(ProdutoParaCarrinho item : carrinho.getProdutos().values()){
            Produto produto = produtoMongoRepository.findById(item.getProduto().getId()).orElse(null);
            if(produto != null) {
                produto.setTotalUnidades(produto.getTotalUnidades() - item.getQuantidade());
                produtoMongoRepository.save(produto);
            }
        }
    }

    @Override
    public List<Produto> pegarTodosProdutos() {
        return produtoMongoRepository.findAll();
    }
}
