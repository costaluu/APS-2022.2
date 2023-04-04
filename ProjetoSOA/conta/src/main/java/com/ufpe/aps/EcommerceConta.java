package com.ufpe.aps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EcommerceConta {
	public static void main(String[] args) {
		SpringApplication.run(EcommerceConta.class, args);
	}

}
