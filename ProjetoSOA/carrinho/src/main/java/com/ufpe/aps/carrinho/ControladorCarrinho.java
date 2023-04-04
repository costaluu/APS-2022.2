package com.ufpe.aps.carrinho;

import com.ufpe.aps.communication.ControleConta;
import com.ufpe.aps.conta.IRepositorioConta;
import com.ufpe.aps.exception.AccountNotFoundException;
import com.ufpe.aps.pojo.AddProdutoCarrinhoDTO;
import com.ufpe.aps.produto.ControleProduto;
import com.ufpe.aps.produto.Produto;
import com.ufpe.aps.produtoparacarrinho.ProdutoParaCarrinho;
import org.springframework.stereotype.Component;

@Component
public class ControladorCarrinho {

    private final IRepositorioConta repositorioConta;

    private final ControleProduto controleProduto;


    public ControladorCarrinho(IRepositorioConta repositorioConta, ControleProduto controleProduto) {
        this.repositorioConta = repositorioConta;
        this.controleProduto = controleProduto;
    }

    public void addProdutoCarrinho(AddProdutoCarrinhoDTO produtoDTO) throws AccountNotFoundException {
        if(!repositorioConta.checarExistencia(produtoDTO.getLogin()))
            throw new AccountNotFoundException();
        Produto produto = null;
        try {
            produto = controleProduto.pegarProduto(produtoDTO.getIdProduto(), produtoDTO.getQuantidade());
        } catch (Exception e) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        if(produto == null)
            throw new IllegalArgumentException("Produto não encontrado");

        Carrinho carrinho = repositorioConta.pegarConta(produtoDTO.getLogin()).getCarrinho();
        ProdutoParaCarrinho produtoParaCarrinho = ProdutoParaCarrinho.builder()
                .produto(produto)
                .quantidade(produtoDTO.getQuantidade())
                .build();

        carrinho.addProduto(produtoParaCarrinho);

        repositorioConta.atualizarCarrinho(produtoDTO.getLogin(), carrinho);
    }

    public Carrinho getProdutosCarrinho(String contaId) throws AccountNotFoundException {
        if(!repositorioConta.checarExistencia(contaId))
            throw new AccountNotFoundException();
        return repositorioConta.pegarConta(contaId).getCarrinho();
    }
}
