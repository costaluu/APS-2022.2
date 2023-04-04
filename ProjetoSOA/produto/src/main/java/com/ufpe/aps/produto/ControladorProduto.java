package com.ufpe.aps.produto;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.exception.ProdutoNotFoundException;
import com.ufpe.aps.exception.QuantidadeProdutoException;
import com.ufpe.aps.produto.Produto;
import com.ufpe.aps.produto.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ControladorProduto {

    private final IRepositorioProduto repositorioProduto;

    @Autowired
    public ControladorProduto(IRepositorioProduto repositorioProduto) {
        this.repositorioProduto = repositorioProduto;
    }

    public void publicarItem(Produto produtoDTO) {
        Produto produto = repositorioProduto.pegarProduto(produtoDTO.getId());
        if (produto != null) {
            produtoDTO.setTotalUnidades(produto.getTotalUnidades() + produtoDTO.getTotalUnidades());
        }
        repositorioProduto.addAoEstoque(produtoDTO);
    }

    public List<Produto> pegarTodosProdutos() {
        return repositorioProduto.pegarTodosProdutos();
    }

    public Produto pegarProduto(String id, Integer quantidade) throws ProdutoNotFoundException, QuantidadeProdutoException {
        Produto produto = repositorioProduto.pegarProduto(id, quantidade);
        if (produto == null) {
            throw new ProdutoNotFoundException();
        }

        if(produto.getTotalUnidades() < quantidade) {
            throw new QuantidadeProdutoException("Quantidade do produto maior que a disponível");
        }

        return produto;
    }

    public void atualizaEstoque(Carrinho carrinho) {
        repositorioProduto.atualizaEstoquesProdutos(carrinho);
    }
}
