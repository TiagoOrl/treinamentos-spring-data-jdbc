package com.teste.treinamentos.config;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("treinamentosapp-public")
                .displayName("Treinamentos RH")
                .pathsToMatch("/api/curso/**", "/api/funcionario/**", "/api/turma/**")
                .build();
    }

}
