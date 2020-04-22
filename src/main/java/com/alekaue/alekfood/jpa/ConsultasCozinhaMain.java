package com.alekaue.alekfood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.alekaue.alekfood.AlekfoodApiApplication;
import com.alekaue.alekfood.domain.model.Cozinha;
import com.alekaue.alekfood.domain.repository.CozinhaRepository;

public class ConsultasCozinhaMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlekfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
	CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);
	
	List<Cozinha> todasCozinhas = cozinhas.listar();
	
	for (Cozinha cozinha : todasCozinhas) {
		System.out.println(cozinha.getNome());
	}
	
	}
	
}
