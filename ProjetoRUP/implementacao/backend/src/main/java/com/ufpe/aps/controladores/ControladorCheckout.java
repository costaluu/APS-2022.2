package com.ufpe.aps.controladores;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.conta.CadastroConta;
import com.ufpe.aps.conta.Conta;
import com.ufpe.aps.pedido.Pedido;
import com.ufpe.aps.produto.CadastroProduto;
import com.ufpe.aps.sistemaoperadoracartao.IComunicacaoOperadoraCartao;
import com.ufpe.aps.pojo.PagamentoDTO;
import com.ufpe.aps.pedido.CadastroPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class ControladorCheckout {

    private final IComunicacaoOperadoraCartao comunicacaoOperadoraCartao;

    private final CadastroConta cadastroConta;

    private final CadastroProduto cadastroProduto;

    private final CadastroPedido cadastroPedido;

    @Autowired
    public ControladorCheckout(CadastroProduto cadastroProduto,
                               CadastroConta cadastroConta,
                               CadastroPedido cadastroPedido,
                               IComunicacaoOperadoraCartao comunicacaoOperadoraCartao) {
        this.cadastroProduto = cadastroProduto;
        this.cadastroConta = cadastroConta;
        this.cadastroPedido = cadastroPedido;
        this.comunicacaoOperadoraCartao = comunicacaoOperadoraCartao;
    }

    public void realizarPagamento(PagamentoDTO pagamentoDTO) throws HttpClientErrorException {

        Conta conta = cadastroConta.pegarConta(pagamentoDTO.getLogin());
        if (conta == null) {
            throw new HttpClientErrorException(HttpStatus.valueOf(500), "Conta não encontrada");
        }
        Carrinho meuCarrinho = conta.pegarCarrinho();

        if (meuCarrinho == null) {
            throw new HttpClientErrorException(HttpStatus.valueOf(500), "Carrinho não encontrado");
        }

        Pedido pedido = cadastroPedido.criarPedido(conta.getLogin(), meuCarrinho);

        try {
            comunicacaoOperadoraCartao.finalizarPagamento(conta.getLogin(), pagamentoDTO.getNumCartao(),
                    pagamentoDTO.getCodSeguranca(), pagamentoDTO.getValidade(), pagamentoDTO.getNomeNoCartao(), pedido);
        } catch (HttpClientErrorException e) {
            String message = "";
            if(e.getStatusCode() == HttpStatus.UNAUTHORIZED) message = "Pagamento não autorizado";
            else message = "Erro ao finalizar pagamento";
            throw new HttpClientErrorException(e.getStatusCode(), message);
        }


        cadastroPedido.confirmarPedido(conta.getLogin(), pedido);

        cadastroProduto.atualizaEstoquesProdutos(meuCarrinho);

        conta.pegarCarrinho().esvaziarCarrinho();

        cadastroConta.atualizarCarrinho(conta.getLogin(), conta.pegarCarrinho());
    }
}
