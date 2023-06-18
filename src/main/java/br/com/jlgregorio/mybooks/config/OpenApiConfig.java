package br.com.jlgregorio.mybooks.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(
                new Info().title("My Books REST Application")
                        .version("1.0")
                        .description("A custom documentation of My Books")
                        .termsOfService("https://www.jlgregorio.com.br")
                        .license( new License().name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                        )
        );
    }

}
