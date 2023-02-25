package com.ufpe.aps;

import com.ufpe.aps.factory.FabricaAbstrataRepositorios;
import com.ufpe.aps.factory.impl.FabricaRepositorioMongo;
import com.ufpe.aps.factory.impl.FabricaRepositoriosBDR;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
