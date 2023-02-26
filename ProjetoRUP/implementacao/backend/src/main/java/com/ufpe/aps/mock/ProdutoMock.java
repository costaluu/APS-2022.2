package com.ufpe.aps.mock;


import com.ufpe.aps.entity.Produto;

public class ProdutoMock {
    public static Produto getProduto() {
        Produto produto = new Produto();
        produto.setId("1");
        produto.setNome("Produto 1");
        produto.setDescricao("Descrição do produto 1");
        produto.setValor(10.0);
        return produto;
    }
}
