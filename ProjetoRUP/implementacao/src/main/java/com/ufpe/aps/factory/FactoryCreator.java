package com.ufpe.aps.factory;

import com.ufpe.aps.factory.impl.FabricaRepositorioMongo;
import com.ufpe.aps.factory.impl.FabricaRepositoriosBDR;

public class FactoryCreator {
    public static FabricaAbstrataRepositorios getFactory(String choice){
        if(choice.equalsIgnoreCase("MONGO")){
            return new FabricaRepositorioMongo();
        } else if(choice.equalsIgnoreCase("BDR")){
            return new FabricaRepositoriosBDR();
        }
        return null;
    }
}
