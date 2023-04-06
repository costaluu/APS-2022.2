package com.ufpe.aps.produtoparacarrinho;

import com.ufpe.aps.produto.Produto;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoParaCarrinho {
    private Produto produto;
    private Integer quantidade;

    public static ProdutoParaCarrinho criarProdutoParaPedido(Produto produto, Integer quantidade){
        ProdutoParaCarrinho produtoParaCarrinho = new ProdutoParaCarrinho();
        produtoParaCarrinho.setProduto(produto);
        produtoParaCarrinho.setQuantidade(quantidade);
        return produtoParaCarrinho;
    }
}
