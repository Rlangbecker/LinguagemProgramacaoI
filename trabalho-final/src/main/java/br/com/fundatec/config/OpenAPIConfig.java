package br.com.fundatec.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Trabalho Final")
                        .description("Trabalho Final LP1 - T21")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://fundatec.com.br")))
                .externalDocs(new ExternalDocumentation()
                        .description("Wiki")
                        .url("https://wiki.org/docs"));
    }

}