package com.ufpe.aps.repository.inmemory;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.conta.RegistroConta;
import com.ufpe.aps.conta.IRepositorioConta;

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

        private Map<String, RegistroConta> contas;

        public CadastroContaRepositoryInMemory() {
            contas = new HashMap<>();
            contas.put("teste1", new RegistroConta("teste1", "teste1", new Carrinho()));
            contas.put("teste2", new RegistroConta("teste2", "teste2", new Carrinho()));
            System.out.println("Contas criadas: " + contas.size());
        }

        @Override
        public List<RegistroConta> getAll() {
            return List.copyOf(contas.values());
        }

        @Override
        public boolean checarExistencia(String login) {
            return contas.get(login) != null;
        }

        @Override
        public void criarConta(String login, String senha) {
            RegistroConta registroConta = new RegistroConta();
            registroConta.setLogin(login);
            registroConta.setSenha(senha);
            registroConta.setCarrinho(new Carrinho());
            contas.put(login, registroConta);
        }

        @Override
        public RegistroConta pegarConta(String login) {
            return contas.get(login);
        }

        @Override
        public void atualizarCarrinho(String login, Carrinho carrinho) {
            RegistroConta registroConta = pegarConta(login);
            registroConta.setCarrinho(carrinho);
            contas.remove(login);
            contas.put(login, registroConta);
        }

    @Override
    public void deletarConta(RegistroConta registroConta) {
        contas.remove(registroConta.getLogin());
    }

}
