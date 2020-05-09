package com.alekaue.alekfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.alekaue.alekfood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlekfoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlekfoodApiApplication.class, args);
	}

}
