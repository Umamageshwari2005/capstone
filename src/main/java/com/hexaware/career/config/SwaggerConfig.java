package com.hexaware.career.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI() {

        final String securitySchemeName = "Bearer Authentication";

        return new OpenAPI()

                .info(new Info()

                        .title("CareerCrafter API")

                        .description("CareerCrafter Job Portal REST APIs")

                        .version("1.0")

                        .contact(new Contact()

                                .name("Uma Mageshwari")

                                .email("umabio2005@gmail.com")))

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName))

                .components(

                        new Components()

                                .addSecuritySchemes(

                                        securitySchemeName,

                                        new SecurityScheme()

                                                .name(securitySchemeName)

                                                .type(SecurityScheme.Type.HTTP)

                                                .scheme("bearer")

                                                .bearerFormat("JWT")

                                )

                );

    }

}