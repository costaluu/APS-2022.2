package com.ufpe.aps.controladores;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.conta.CadastroConta;
import com.ufpe.aps.conta.Conta;
import com.ufpe.aps.produto.CadastroProduto;
import com.ufpe.aps.produto.Produto;
import com.ufpe.aps.produtoparacarrinho.ProdutoParaCarrinho;
import com.ufpe.aps.conta.IRepositorioConta;
import com.ufpe.aps.produto.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorAddProdutoCarrinho {


    private final CadastroProduto cadastroProduto;

    private final CadastroConta cadastroConta;

    @Autowired
    public ControladorAddProdutoCarrinho(CadastroProduto cadastroProduto, CadastroConta cadastroConta) {
        this.cadastroConta = cadastroConta;
        this.cadastroProduto = cadastroProduto;
    }

    public void addProdutoCarrinho(String login, String idProduto, Integer quantidade) throws Exception {
        Conta conta = cadastroConta.pegarConta(login);
        Carrinho meuCarrinho = conta.pegarCarrinho();

        Produto produto = cadastroProduto.pegarProduto(idProduto, quantidade);
        if (produto == null)
            throw new Exception("Produto e/ou quantidade não disponível no estoque");

        ProdutoParaCarrinho produtoParaCarrinho = ProdutoParaCarrinho.criarProdutoParaPedido(produto, quantidade);

        meuCarrinho.addProduto(produtoParaCarrinho);

        if(meuCarrinho.getProdutos().get(idProduto).getQuantidade() > produto.getTotalUnidades())
            throw new Exception("Quantidade de produtos no carrinho maior que a quantidade disponível no estoque");

        cadastroConta.atualizarCarrinho(conta.getLogin(), meuCarrinho);
    }
}
