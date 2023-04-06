package com.ufpe.aps.carrinho;

import com.ufpe.aps.exception.AccountNotFoundException;
import com.ufpe.aps.pojo.AddProdutoCarrinhoDTO;

public interface IServicoCarrinho {
    void addProdutoCarrinho(AddProdutoCarrinhoDTO produtoDTO) throws AccountNotFoundException;

    void removerProdutoCarrinho(String login, String produtoId) throws AccountNotFoundException;

    void atualizarProdutoCarrinho(String login, String produtoId, int quantidade) throws AccountNotFoundException;

    Carrinho pegarMeuCarrinho(String login) throws AccountNotFoundException;
}
