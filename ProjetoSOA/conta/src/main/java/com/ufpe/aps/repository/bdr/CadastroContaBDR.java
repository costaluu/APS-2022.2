package com.ufpe.aps.repository.bdr;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.conta.Conta;
import com.ufpe.aps.conta.IRepositorioConta;
import com.ufpe.aps.exception.AccountNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CadastroContaBDR implements IRepositorioConta {

    private final ContaDAO contaDAO;
    public CadastroContaBDR(ContaDAO contaDAO) {
        this.contaDAO = contaDAO;
    }

    @Override
    public List<Conta> getAll() {
        return contaDAO.findAll();
    }

    @Override
    public boolean checarExistencia(String login) {
        return contaDAO.findById(login).isPresent();
    }

    @Override
    public void criarConta(String login, String senha) {
        contaDAO.save(new Conta(login, senha, new Carrinho()));
    }

    @Override
    public Conta pegarConta(String login) throws AccountNotFoundException {
        return contaDAO.findById(login).orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public void atualizarCarrinho(String login, Carrinho carrinho) throws AccountNotFoundException {
        contaDAO.findById(login).ifPresentOrElse(conta -> {
            conta.setCarrinho(carrinho);
            contaDAO.save(conta);
        }, () -> {
            try {
                throw new AccountNotFoundException();
            } catch (AccountNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void deletarConta(Conta conta) {
        contaDAO.delete(conta);
    }
}
