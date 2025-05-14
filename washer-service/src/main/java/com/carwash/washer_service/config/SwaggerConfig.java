package com.carwash.washer_service.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig  {

    @Bean
    public OpenAPI washerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Washer Service API")
                        .description("CRUD operations and profile/order management for Washers")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("i-Transform Dev Team")
                                .email("support@itransform.com")
                                .url("https://itransform.com")
                        )
                )
                .servers(List.of(
                        new Server().url("http://localhost:8084").description("Generated server url")
                ));
    }

    @Bean
    public GroupedOpenApi washerApiGroup() {
        return GroupedOpenApi.builder()
                .group("washer-service")
                .pathsToMatch("/api/washers/**")
                .build();
    }
}
