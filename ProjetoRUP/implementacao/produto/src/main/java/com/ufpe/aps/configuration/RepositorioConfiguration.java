package com.ufpe.aps.configuration;

import com.ufpe.aps.factory.impl.FabricaRepositorioMongo;
import com.ufpe.aps.factory.impl.FabricaRepositoriosInMemory;
import com.ufpe.aps.produto.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
@Scope("singleton")
public class RepositorioConfiguration {

//    @Value("${fabrica.repositorios.choice}")
    private final String choice;

    FabricaRepositorioMongo fabricaRepositorioMongo;

    FabricaRepositoriosInMemory fabricaRepositoriosInMemory;

    @Autowired
    public RepositorioConfiguration(FabricaRepositorioMongo fabricaRepositorioMongo,
                                    FabricaRepositoriosInMemory fabricaRepositoriosInMemory,
                                    Environment environment) {
        this.fabricaRepositorioMongo = fabricaRepositorioMongo;
        this.fabricaRepositoriosInMemory = fabricaRepositoriosInMemory;
        this.choice = environment.getProperty("fabrica.repositorios.choice");
        System.out.println("RepositorioConfiguration");
    }


    @Bean
    public IRepositorioProduto repositorioProduto() {
        if(choice.equalsIgnoreCase("MONGO"))
            return fabricaRepositorioMongo.criarRepositorioProduto();
        return new FabricaRepositoriosInMemory().criarRepositorioProduto();
    }
}
