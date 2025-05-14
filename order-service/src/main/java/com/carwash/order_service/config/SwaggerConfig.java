package com.carwash.order_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI orderServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Service API")
                        .description("API for managing Orders and Packages in i-Transform Car Wash")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("i-Transform Dev Team")
                                .email("support@itransform.com")
                                .url("https://itransform.com")
                        )
                )
                .servers(List.of(
                        new Server().url("http://localhost:8084").description("Order Service Local Server")
                ));
    }

    // Swagger group for Order endpoints
    @Bean
    public GroupedOpenApi orderApiGroup() {
        return GroupedOpenApi.builder()
                .group("Orders")
                .pathsToMatch("/api/orders/**")
                .build();
    }

    // Swagger group for Plan endpoints (if you want them separately)
    @Bean
    public GroupedOpenApi packageApiGroup() {
        return GroupedOpenApi.builder()
                .group("Plans")
                .pathsToMatch("/api/plans/**")
                .build();
    }
}
