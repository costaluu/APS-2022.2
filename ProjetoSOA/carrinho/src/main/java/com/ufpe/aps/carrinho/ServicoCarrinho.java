package com.ufpe.aps.carrinho;

import com.ufpe.aps.conta.IRegistroConta;
import com.ufpe.aps.exception.AccountNotFoundException;
import com.ufpe.aps.pojo.AddProdutoCarrinhoDTO;
import com.ufpe.aps.produto.Produto;
import com.ufpe.aps.produto.RegistroProduto;
import com.ufpe.aps.produtoparacarrinho.ProdutoParaCarrinho;
import jakarta.ws.rs.ServerErrorException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ServicoCarrinho implements IServicoCarrinho {

    private final IRegistroConta registroConta;

    private final RegistroProduto registroProduto;

    public ServicoCarrinho(IRegistroConta registroConta, RegistroProduto registroProduto) {
        this.registroConta = registroConta;
        this.registroProduto = registroProduto;
    }


    @Override
    public void addProdutoCarrinho(AddProdutoCarrinhoDTO produtoDTO) throws AccountNotFoundException {
        Produto produto = null;
        try {
            produto = registroProduto.pegarProduto(produtoDTO.getIdProduto(), produtoDTO.getQuantidade());
        } catch (Exception e) {
            throw new ServerErrorException("Erro ao adicionar produto ao carrinho", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }

        if(produto == null)
            throw new IllegalArgumentException("Produto não encontrado");

        Carrinho carrinho = registroConta.pegarCarrinho(produtoDTO.getLogin());
        ProdutoParaCarrinho produtoParaCarrinho = ProdutoParaCarrinho.builder()
                .produto(produto)
                .quantidade(produtoDTO.getQuantidade())
                .build();

        carrinho.addProduto(produtoParaCarrinho);

        registroConta.atualizarCarrinho(produtoDTO.getLogin(), carrinho);
    }

    @Override
    public void removerProdutoCarrinho(String login, String produtoId) throws AccountNotFoundException {
        Carrinho carrinho = registroConta.pegarCarrinho(login);
        for(ProdutoParaCarrinho produtoParaCarrinho : carrinho.getProdutos().values()) {
            if(produtoParaCarrinho.getProduto().getId().equals(produtoId)) {
                carrinho.getProdutos().remove(produtoParaCarrinho.getProduto().getId());
                break;
            }
        }
        registroConta.atualizarCarrinho(login, carrinho);
    }

    @Override
    public void atualizarProdutoCarrinho(String login, String produtoId, int quantidade) throws AccountNotFoundException {
        Carrinho carrinho = registroConta.pegarCarrinho(login);
        for(ProdutoParaCarrinho produtoParaCarrinho : carrinho.getProdutos().values()) {
            if(produtoParaCarrinho.getProduto().getId().equals(produtoId)) {
                produtoParaCarrinho.setQuantidade(quantidade);
                break;
            }
        }
        registroConta.atualizarCarrinho(login, carrinho);
    }

    @Override
    public Carrinho pegarMeuCarrinho(String login) throws AccountNotFoundException {
        return registroConta.pegarCarrinho(login);
    }
}
