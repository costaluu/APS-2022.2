package com.ufpe.aps.produto;

import com.ufpe.aps.exception.IsNotOwnerOfProductException;

import java.util.List;

public interface IServicoProduto {

    List<Produto> meusProdutos(String login);

    List<Produto> pegarTodosProdutos();

    void publicarItem(Produto produto);

    void excluirProduto(String login, String idProduto) throws IsNotOwnerOfProductException;

    void avaliar(String login, String idProduto, Integer avaliacao);
}
