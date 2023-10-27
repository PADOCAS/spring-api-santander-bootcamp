package com.ldsystems.santander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

// Configurar o Railway.toml para utilizar no servidor do Railway
// Necessário para rodar em HTTPS (Servidor Railway por exemplo)
@OpenAPIDefinition(servers = { @Server(url = "/", description = "URL Servidor Default") })  // Deploy para o TomCat / Railway
//@OpenAPIDefinition(servers = { @Server(url = "/spring-api-santander-bootcamp", description = "URL Servidor Default") })  // Deploy Payara Micro
@SpringBootApplication
@ComponentScan
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
