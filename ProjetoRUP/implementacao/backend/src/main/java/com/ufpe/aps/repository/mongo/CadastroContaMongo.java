package com.ufpe.aps.repository.mongo;

import com.ufpe.aps.entity.Carrinho;
import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.repository.IRepositorioConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Scope("singleton")
public class CadastroContaMongo implements IRepositorioConta {

    private final ContaMongoRepository contaMongoRepository;

    private static int count = 0;


    @Autowired
    public CadastroContaMongo(ContaMongoRepository contaMongoRepository) {
        this.contaMongoRepository = contaMongoRepository;
        System.out.println("Criando repositorio de conta em mongo " + count++);
    }

    @Override
    public List<Conta> getAll() {
        return this.contaMongoRepository.findAll();
    }

    @Override
    public boolean checarExistencia(String login) {
        Optional<Conta> conta = this.contaMongoRepository.findById(login);
        return conta.isPresent();
    }

    @Override
    public void criarConta(String login, String senha) {
        this.contaMongoRepository.save(new Conta(login, senha, new Carrinho()));
    }

    @Override
    public Conta pegarConta(String login) {
        Optional<Conta> conta = this.contaMongoRepository.findById(login);
        return conta.orElse(null);
    }

    @Override
    public void atualizarCarrinho(String login, Carrinho carrinho) {
        Conta conta = pegarConta(login);
        conta.setCarrinho(carrinho);
        this.contaMongoRepository.save(conta);
    }
}
