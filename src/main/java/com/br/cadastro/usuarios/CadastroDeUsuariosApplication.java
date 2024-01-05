package com.br.cadastro.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.br.cadastro.usuarios.domain.entities"})
@EnableJpaRepositories(basePackages = {"com.br.cadastro.usuarios.domain.repositories"})
@ComponentScan(basePackages = {"com.br.cadastro.usuarios"})
@SpringBootApplication
public class CadastroDeUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroDeUsuariosApplication.class, args);
	}

}
