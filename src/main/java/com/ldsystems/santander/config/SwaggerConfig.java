package com.ldsystems.santander.config;

import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI testOpenAPIDefinition() {
        License licenca = new License();
        licenca.setName("Licença - LD Systems");
        licenca.setUrl("http://www.ldsystems.com.br/portfolio");

        return new OpenAPI()
                .info(new Info().title("Aplicação de teste")
                        .contact(new Contact().name("Equipe LD Systems").email("teste@ldsystems.com.br"))
                        .termsOfService("Termo de uso: Open Source")
                        .license(licenca)
                        .description("Aplicação desenvolvida para testes.")
                        .version("v0.0.1"));
    }
}