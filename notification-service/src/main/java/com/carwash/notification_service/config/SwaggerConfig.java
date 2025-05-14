package com.carwash.notification_service.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI notificationServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Notification Service API")
                        .description("API for managing notifications in the i-Transform Car Wash App")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("i-Transform Dev Team")
                                .email("support@itransform.com")
                                .url("https://itransform.com")
                        )
                )
                .servers(List.of(
                        new Server().url("http://localhost:8089").description("Notification Service Local Server")
                ));
    }

    @Bean
    public GroupedOpenApi notificationApiGroup() {
        return GroupedOpenApi.builder()
                .group("Notifications")
                .pathsToMatch("/api/notifications/**")
                .build();
    }
}
