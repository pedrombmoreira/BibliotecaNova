package com.example.novabiblioteca;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(
                title = "API Biblioteca",
                version = "1.0",
                description = "Documentação da API Biblioteca",
                contact = @Contact(name = "Suporte", email = "suporte@exemplo.com")
        )
)
@SpringBootApplication
public class NovaBibliotecaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovaBibliotecaApplication.class, args);
    }

}
