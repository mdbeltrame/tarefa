package br.com.sysmo.tarefa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Tarefas API").version("1.0")
				.description("Documentação da API com Swagger no Spring Boot 3.4.4."));
		
	}
}
