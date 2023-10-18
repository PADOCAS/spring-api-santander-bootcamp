package com.ldsystems.santander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

// Configurar o Railway.toml para utilizar no servidor do Railway
// Necessário para rodar em HTTPS (Servidor Railway por exemplo)																						
@OpenAPIDefinition(servers = { @Server(url = "/", description = "URL Servidor Default") })
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
