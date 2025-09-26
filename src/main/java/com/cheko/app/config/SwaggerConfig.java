package com.cheko.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI chekoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cheko API")
                        .description("Cheko Backend Application API Documentation")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Cheko Team")
                                .email("info@cheko.com")));
    }
}
