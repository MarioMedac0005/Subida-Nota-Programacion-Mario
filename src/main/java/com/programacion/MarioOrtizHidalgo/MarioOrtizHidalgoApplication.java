package com.programacion.MarioOrtizHidalgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				description = "Documentación sobre la API Rest para poder hacer un CRUD sobre una lista de productos.",
				version = "1.0",
				contact = @Contact(
						email = "ortizhidalgomario@gmail.com",
						name = "Mario Ortiz Hidalgo"
				),
				title = "API Rest para la creación de una lista de productos básicos."
		)
)
@SpringBootApplication
public class MarioOrtizHidalgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarioOrtizHidalgoApplication.class, args);
	}

}
