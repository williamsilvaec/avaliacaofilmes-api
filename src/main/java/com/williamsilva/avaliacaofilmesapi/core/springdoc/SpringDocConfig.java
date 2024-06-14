package com.williamsilva.avaliacaofilmesapi.core.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI springDocOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Avaliação de Filmes")
                        .description("API Avaliação de Filmes")
                        .version("v2.3.0"));
    }
}