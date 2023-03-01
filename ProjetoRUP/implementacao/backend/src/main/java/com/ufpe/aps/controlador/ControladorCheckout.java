package com.ufpe.aps.controlador;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.entity.Pedido;
import com.ufpe.aps.externo.IComunicacaoOperadoraCartao;
import com.ufpe.aps.pojo.PagamentoDTO;
import com.ufpe.aps.repository.IRepositorioConta;
import com.ufpe.aps.repository.IRepositorioPedido;
import com.ufpe.aps.repository.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class ControladorCheckout {

    private final IComunicacaoOperadoraCartao comunicacaoOperadoraCartao;

    private final IRepositorioConta repositorioConta;

    private final IRepositorioProduto repositorioProduto;

    private final IRepositorioPedido repositorioPedido;

    @Autowired
    public ControladorCheckout(IRepositorioProduto repositorioProduto,
                               IRepositorioConta repositorioConta,
                               IRepositorioPedido repositorioPedido,
                               IComunicacaoOperadoraCartao comunicacaoOperadoraCartao) {
        this.repositorioProduto = repositorioProduto;
        this.repositorioConta = repositorioConta;
        this.repositorioPedido = repositorioPedido;
        this.comunicacaoOperadoraCartao = comunicacaoOperadoraCartao;
    }

    public void realizarPagamento(PagamentoDTO pagamentoDTO) throws HttpClientErrorException {

        Conta conta = repositorioConta.pegarConta(pagamentoDTO.getLogin());
        if (conta == null) {
            throw new HttpClientErrorException(HttpStatus.valueOf(500), "Conta não encontrada");
        }
        Carrinho meuCarrinho = conta.pegarCarrinho();

        if (meuCarrinho == null) {
            throw new HttpClientErrorException(HttpStatus.valueOf(500), "Carrinho não encontrado");
        }

        Pedido pedido = repositorioPedido.criarPedido(conta.getLogin(), meuCarrinho);

        try {
            comunicacaoOperadoraCartao.finalizarPagamento(conta.getLogin(), pagamentoDTO.getNumCartao(),
                    pagamentoDTO.getCodSeguranca(), pagamentoDTO.getValidade(), pagamentoDTO.getNomeNoCartao(), pedido);
        } catch (HttpClientErrorException e) {
            String message = "";
            if(e.getStatusCode() == HttpStatus.UNAUTHORIZED) message = "Pagamento não autorizado";
            else message = "Erro ao finalizar pagamento";
            throw new HttpClientErrorException(e.getStatusCode(), message);
        }


        repositorioPedido.confirmarPedido(conta.getLogin(), pedido);

        repositorioProduto.atualizaEstoquesProdutos(meuCarrinho);

        conta.pegarCarrinho().esvaziarCarrinho();
    }
}
