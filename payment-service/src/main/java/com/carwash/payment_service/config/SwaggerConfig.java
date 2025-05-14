package com.carwash.payment_service.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    /**
     * Configures OpenAPI documentation for the Payment Service.
     *
     * @return OpenAPI documentation settings.
     */
    @Bean
    public OpenAPI paymentServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Payment Service API")
                        .description("API for processing payments and generating receipts in i-Transform Car Wash")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("i-Transform Dev Team")
                                .email("support@itransform.com")
                                .url("https://itransform.com")
                        )
                )
                .servers(List.of(
                        new Server().url("http://localhost:8087").description("Payment Service Local Server")
                ));
    }

    /**
     * Swagger group for Payment endpoints.
     *
     * @return GroupedOpenApi with payment-specific endpoints.
     */
    @Bean
    public GroupedOpenApi paymentApiGroup() {
        return GroupedOpenApi.builder()
                .group("Payments")
                .pathsToMatch("/api/v1/pay/**")
                .build();
    }
}
