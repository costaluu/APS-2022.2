package com.ufpe.aps.controlador;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.entity.Produto;
import com.ufpe.aps.entity.ProdutoParaCarrinho;
import com.ufpe.aps.repository.IRepositorioConta;
import com.ufpe.aps.repository.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorAddProdutoCarrinho {


    private final IRepositorioProduto repositorioProduto;

    private final IRepositorioConta repositorioConta;

    @Autowired
    public ControladorAddProdutoCarrinho(IRepositorioProduto repositorioProduto, IRepositorioConta repositorioConta) {
        this.repositorioConta = repositorioConta;
        this.repositorioProduto = repositorioProduto;
    }

    public void addProdutoCarrinho(String login, String idProduto, Integer quantidade) throws Exception {
        Conta conta = repositorioConta.pegarConta(login);
        Carrinho meuCarrinho = conta.pegarCarrinho();

        Produto produto = repositorioProduto.pegarProduto(idProduto, quantidade);
        if (produto == null)
            throw new Exception("Produto e/ou quantidade não disponível no estoque");

        ProdutoParaCarrinho produtoParaCarrinho = ProdutoParaCarrinho.criarProdutoParaPedido(produto, quantidade);

        meuCarrinho.addProduto(produtoParaCarrinho);

        if(meuCarrinho.getProdutos().get(idProduto).getQuantidade() > produto.getTotalUnidades())
            throw new Exception("Quantidade de produtos no carrinho maior que a quantidade disponível no estoque");

        repositorioConta.atualizarCarrinho(conta.getLogin(), meuCarrinho);
    }
}
