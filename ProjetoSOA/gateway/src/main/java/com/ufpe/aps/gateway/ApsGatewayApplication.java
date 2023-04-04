package com.ufpe.aps.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApsGatewayApplication.class, args);
	}

}
