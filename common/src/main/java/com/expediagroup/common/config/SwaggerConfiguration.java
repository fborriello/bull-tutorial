package com.expediagroup.common.config;

import static java.util.Collections.emptyList;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger configuration.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Value("${swagger.enabled}")
    private boolean swaggerEnabled;
    @Value("${swagger.api.info.title}")
    private String title;
    @Value("${swagger.api.info.description}")
    private String description;
    @Value("${swagger.api.info.version}")
    private String version;
    @Value("${swagger.api.info.contact.name}")
    private String contactName;
    @Value("${swagger.api.info.contact.email}")
    private String contactEmail;

    /**
     * Initialize swagger properties.
     * @return {@link PropertySourcesPlaceholderConfigurer}
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer swaggerProperties() {
        var p = new PropertySourcesPlaceholderConfigurer();
        p.setIgnoreUnresolvablePlaceholders(true);
        return p;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(withClassAnnotation(RestController.class))
                .paths(any())
                .build().apiInfo(apiInfo()).enable(swaggerEnabled);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(title, description, version, null, contact(), null, null, emptyList());
    }

    private Contact contact() {
        return new Contact(contactName, null, contactEmail);
    }
}
