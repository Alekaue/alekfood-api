package com.alekaue.alekfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.alekaue.alekfood.AlekfoodApiApplication;
import com.alekaue.alekfood.domain.model.Cozinha;
import com.alekaue.alekfood.domain.repository.CozinhaRepository;

public class RemocaoCozinhaMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlekfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
	Cozinha cozinha = new Cozinha();
	cozinha.setId(1L);
	
	
	cozinhaRepository.remover(cozinha.getId());
	
	
	}
}