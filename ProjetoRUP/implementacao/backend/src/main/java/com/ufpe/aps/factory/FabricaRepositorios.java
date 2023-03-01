package com.ufpe.aps.factory;

import com.ufpe.aps.factory.impl.FabricaRepositorioMongo;
import com.ufpe.aps.factory.impl.FabricaRepositoriosInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class FabricaRepositorios {

    private static FabricaRepositoriosInMemory fabricaRepositoriosInMemory;
    private static FabricaRepositorioMongo fabricaRepositorioMongo;

//    @Value("${fabrica.repositorios.choice}")
    private static final String choice = System.getenv("FABRICA_REPOSITORIOS_CHOICE") == null
        ? "INMEMORY" : System.getenv("FABRICA_REPOSITORIOS_CHOICE");

    public static synchronized FabricaAbstrataRepositorios pegarFabrica(){
        if(choice.equalsIgnoreCase("MONGO")){
            if(fabricaRepositorioMongo == null){
                fabricaRepositorioMongo = new FabricaRepositorioMongo();
            }
            return fabricaRepositorioMongo;
        }
        if(fabricaRepositoriosInMemory == null){
            fabricaRepositoriosInMemory = new FabricaRepositoriosInMemory();
        }
        return fabricaRepositoriosInMemory;
    }
}
