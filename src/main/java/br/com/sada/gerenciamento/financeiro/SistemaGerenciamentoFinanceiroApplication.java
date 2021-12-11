package br.com.sada.gerenciamento.financeiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class SistemaGerenciamentoFinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaGerenciamentoFinanceiroApplication.class, args);
	}
	
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Gerenciamento Financeiro").description("Sistema de Gerenciamento Financeiro")
						.version("v0.0.1")
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation()
						.description("Doc")
						.url("https://localhost:8080/"));
	}
}
