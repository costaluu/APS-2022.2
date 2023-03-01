package com.ufpe.aps.configuration;

import com.ufpe.aps.factory.impl.FabricaRepositorioMongo;
import com.ufpe.aps.factory.impl.FabricaRepositoriosInMemory;
import com.ufpe.aps.repository.IRepositorioConta;
import com.ufpe.aps.repository.IRepositorioPedido;
import com.ufpe.aps.repository.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositorioConfiguration {

//    @Value("${fabrica.repositorios.choice}")
    private static String choice = "inmemory";
    @Bean
    public IRepositorioConta repositorioConta() {
        if(choice.equals("MONGO"))
            return new FabricaRepositorioMongo().criarRepositorioConta();
        return new FabricaRepositoriosInMemory().criarRepositorioConta();
    }

    @Bean
    public IRepositorioProduto repositorioProduto() {
        if(choice.equals("MONGO"))
            return new FabricaRepositorioMongo().criarRepositorioProduto();
        return new FabricaRepositoriosInMemory().criarRepositorioProduto();
    }

    @Bean
    public IRepositorioPedido repositorioPedido(){
        if(choice.equals("MONGO"))
            return new FabricaRepositorioMongo().criarRepositorioPedido();
        return new FabricaRepositoriosInMemory().criarRepositorioPedido();
    }

}
