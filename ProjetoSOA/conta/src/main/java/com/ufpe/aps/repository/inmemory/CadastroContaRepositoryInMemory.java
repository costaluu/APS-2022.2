package com.ufpe.aps.repository.inmemory;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.conta.Conta;
import com.ufpe.aps.conta.IRepositorioConta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadastroContaRepositoryInMemory implements IRepositorioConta {

        private static CadastroContaRepositoryInMemory instance;

        public static CadastroContaRepositoryInMemory getInstance() {
            if (instance == null) {
                instance = new CadastroContaRepositoryInMemory();
            }
            return instance;
        }

        private final List<Conta> contas;

        public CadastroContaRepositoryInMemory() {
            contas = new ArrayList<>();
            contas.add(new Conta("teste1", "teste1", new Carrinho()));
            contas.add(new Conta("teste2", "teste2", new Carrinho()));
            System.out.println("Contas criadas: " + contas.size());
        }

        @Override
        public List<Conta> getAll() {
            return List.copyOf(contas);
        }

        @Override
        public boolean checarExistencia(String login) {
            return contas.stream().anyMatch(conta -> conta.getLogin().equals(login));
        }

        @Override
        public void criarConta(String login, String senha) {
            Conta conta = new Conta();
            conta.setLogin(login);
            conta.setSenha(senha);
            conta.setCarrinho(new Carrinho());
            contas.add(conta);
        }

        @Override
        public Conta pegarConta(String login) {
            return contas.stream().filter(conta -> conta.getLogin().equals(login)).findFirst().orElse(null);
        }

        @Override
        public void atualizarCarrinho(String login, Carrinho carrinho) {
            Conta conta = pegarConta(login);
            conta.setCarrinho(carrinho);
            contas.stream().filter(conta1 -> conta1.getLogin().equals(login)).findFirst().ifPresent(conta1 -> conta1.setCarrinho(carrinho));
        }

    @Override
    public void deletarConta(Conta conta) {
        contas.stream().filter(conta1 -> conta1.getLogin().equals(conta.getLogin())).findFirst().ifPresent(contas::remove);
    }

}
