package com.alekaue.alekfood.api.controller;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alekaue.alekfood.domain.model.Cozinha;
import com.alekaue.alekfood.domain.model.Restaurante;
import com.alekaue.alekfood.domain.repository.CozinhaRepository;
import com.alekaue.alekfood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping("/cozinha/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome) {
		return cozinhaRepository.findTodasByNome(nome);
	}
	

	@GetMapping("/cozinhas/primeiro")
	public Optional<Cozinha> cozinhaPrimeiro(String nome) {
		return cozinhaRepository.buscarPrimeiro();
	}
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhaPorNome(String nome) {
		return cozinhaRepository.findTodasByNomeContaining(nome);
	}
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantePorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantePorNomeFrete(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	

	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {
		return restauranteRepository.findComFreteGratis(nome);
	}
	
	
	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro(String nome) {
		return restauranteRepository.buscarPrimeiro();
	}

}
