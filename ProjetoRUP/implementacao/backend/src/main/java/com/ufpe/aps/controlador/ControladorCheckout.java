package com.ufpe.aps.controlador;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.entity.Pedido;
import com.ufpe.aps.externo.IComunicacaoOperadoraCartao;
import com.ufpe.aps.pojo.PagamentoDTO;
import com.ufpe.aps.repository.interfaces.IRepositorioConta;
import com.ufpe.aps.repository.interfaces.IRepositorioPedido;
import com.ufpe.aps.repository.interfaces.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControladorCheckout {

    @Autowired
    IComunicacaoOperadoraCartao comunicacaoOperadoraCartao;

    @Autowired
    IRepositorioConta repositorioConta;

    @Autowired
    IRepositorioProduto repositorioProduto;

    @Autowired
    IRepositorioPedido repositorioPedido;

    public void realizarPagamento(PagamentoDTO pagamentoDTO) throws Exception {

        Conta conta = repositorioConta.pegarConta(pagamentoDTO.getLogin());
        if (conta == null) {
            throw new Exception("Conta não encontrada");
        }
        Carrinho meuCarrinho = conta.pegarCarrinho();

        if (meuCarrinho == null) {
            throw new Exception("Carrinho não encontrado");
        }

        Pedido pedido = repositorioPedido.criarPedido(conta.getLogin(), meuCarrinho);
        try {
            comunicacaoOperadoraCartao.finalizarPagamento(conta.getLogin(), pagamentoDTO.getNumCartao(),
                    pagamentoDTO.getCodSeguranca(), pagamentoDTO.getValidade(), pagamentoDTO.getNomeNoCartao(), pedido);
        } catch (Exception e) {
            throw new Exception("Erro ao enviar pagamento");
        }

        repositorioPedido.confirmarPedido(conta.getLogin(), pedido);

        repositorioProduto.atualizaEstoquesProdutos(meuCarrinho);

        conta.pegarCarrinho().esvaziarCarrinho();
    }
}
