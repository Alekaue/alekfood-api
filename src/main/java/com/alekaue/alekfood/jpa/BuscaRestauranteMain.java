package com.alekaue.alekfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.alekaue.alekfood.AlekfoodApiApplication;
import com.alekaue.alekfood.domain.model.Restaurante;
import com.alekaue.alekfood.domain.repository.RestauranteRepository;

public class BuscaRestauranteMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlekfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante = restaurantes.porId(1L);
		
		System.out.println(restaurante.getNome());
	}
	

}
