package com.expediagroup.common.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * Swagger configuration.
 */
@Configuration
public class SwaggerConfiguration {
    @Value("${apiDoc.api.info.title}")
    private String title;
    @Value("${apiDoc.api.info.description}")
    private String description;
    @Value("${apiDoc.api.info.version}")
    private String version;
    @Value("${apiDoc.api.info.contact.name}")
    private String contactName;
    @Value("${apiDoc.api.info.contact.email}")
    private String contactEmail;

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("movie-api")
                .packagesToScan("com.expediagroup.web")
                .build();
    }

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info().title(title)
                        .description(description)
                        .version(version)
                        .contact(contact()));
    }

    private Contact contact() {
        return new Contact()
                .name(contactName)
                .email(contactEmail);
    }
}
