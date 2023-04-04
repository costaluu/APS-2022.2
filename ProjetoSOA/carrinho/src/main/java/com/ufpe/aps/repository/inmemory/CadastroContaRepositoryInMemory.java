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

        private Map<String, Conta> contas;

        public CadastroContaRepositoryInMemory() {
            contas = new HashMap<>();
            contas.put("teste1", new Conta("teste1", "teste1", new Carrinho()));
            contas.put("teste2", new Conta("teste2", "teste2", new Carrinho()));
            System.out.println("Contas criadas: " + contas.size());
        }

        @Override
        public List<Conta> getAll() {
            return List.copyOf(contas.values());
        }

        @Override
        public boolean checarExistencia(String login) {
            return contas.get(login) != null;
        }

        @Override
        public void criarConta(String login, String senha) {
            Conta conta = new Conta();
            conta.setLogin(login);
            conta.setSenha(senha);
            conta.setCarrinho(new Carrinho());
            contas.put(login, conta);
        }

        @Override
        public Conta pegarConta(String login) {
            return contas.get(login);
        }

        @Override
        public void atualizarCarrinho(String login, Carrinho carrinho) {
            Conta conta = pegarConta(login);
            conta.setCarrinho(carrinho);
            contas.remove(login);
            contas.put(login, conta);
        }

}
