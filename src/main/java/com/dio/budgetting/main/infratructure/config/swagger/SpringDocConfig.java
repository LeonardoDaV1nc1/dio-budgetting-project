package com.dio.budgetting.main.infratructure.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
    	
    	final String securitySchemeName = "bearerAuth";
    	
        return new OpenAPI()
                .info(new Info()
                        .title("DIO - Budgetting")
                        .version("1.0")
                        .description("API para gerenciamento transacoes financeiras")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().name("Leonardo da Vinci").email("leodvc82@gmail.com")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                    .addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                            .name(securitySchemeName)
                            .type(SecurityScheme.Type.HTTP) 
                            .scheme("bearer")              
                            .bearerFormat("JWT")));        
    }
}