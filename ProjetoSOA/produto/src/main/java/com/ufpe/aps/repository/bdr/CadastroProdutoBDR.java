package com.ufpe.aps.repository.bdr;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.produto.IRepositorioProduto;
import com.ufpe.aps.produto.Produto;
import com.ufpe.aps.produtoparacarrinho.ProdutoParaCarrinho;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CadastroProdutoBDR implements IRepositorioProduto {

    private final ProdutoDAO produtoDAO;

    public CadastroProdutoBDR(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }


    @Override
    public void addAoEstoque(Produto produto) {
        produtoDAO.save(produto);
    }

    @Override
    public Produto pegarProduto(String idProduto, Integer quantidade) {
        return produtoDAO.findById(idProduto).orElse(null);
    }

    @Override
    public Produto pegarProduto(String idProduto) {
        return produtoDAO.findById(idProduto).orElse(null);
    }

    @Override
    public void atualizaEstoquesProdutos(Carrinho carrinho) {
        for(ProdutoParaCarrinho elem : carrinho.getProdutos().values()){
            Produto produto = pegarProduto(elem.getProduto().getId());
            produto.setTotalUnidades(produto.getTotalUnidades() - elem.getQuantidade());
            produtoDAO.save(produto);
        }
    }

    @Override
    public List<Produto> pegarTodosProdutos() {
        return produtoDAO.findAll();
    }

    @Override
    public void deletarProduto(String idProduto) {
        produtoDAO.deleteById(idProduto);
    }

    @Override
    public void atualizarAvaliacao(String idProduto, String avaliacao) {
        Produto produto = pegarProduto(idProduto);
        produto.setAvaliacao(avaliacao);
        produtoDAO.save(produto);
    }
}
