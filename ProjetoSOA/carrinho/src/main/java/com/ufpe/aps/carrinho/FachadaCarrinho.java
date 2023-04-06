package com.ufpe.aps.carrinho;

import com.ufpe.aps.exception.AccountNotFoundException;
import com.ufpe.aps.pojo.AddProdutoCarrinhoDTO;
import org.springframework.stereotype.Component;

@Component
public class FachadaCarrinho {

    private final IServicoCarrinho servicoCarrinho;

    public FachadaCarrinho(IServicoCarrinho servicoCarrinho) {
        this.servicoCarrinho = servicoCarrinho;
    }


    public void addProdutoCarrinho(AddProdutoCarrinhoDTO produtoDTO) throws AccountNotFoundException {
        servicoCarrinho.addProdutoCarrinho(produtoDTO);
    }

    public void removerProdutoCarrinho(String login, String produtoId) throws AccountNotFoundException {
        servicoCarrinho.removerProdutoCarrinho(login, produtoId);
    }

    public void atualizarProdutoCarrinho(String login, String produtoId, int quantidade) throws AccountNotFoundException {
        servicoCarrinho.atualizarProdutoCarrinho(login, produtoId, quantidade);
    }

    public Carrinho pegarCarrinho(String login) throws AccountNotFoundException {
        return servicoCarrinho.pegarMeuCarrinho(login);
    }
}
