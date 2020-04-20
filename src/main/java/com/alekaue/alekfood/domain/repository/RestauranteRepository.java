package com.alekaue.alekfood.domain.repository;

import java.util.List;

import com.alekaue.alekfood.domain.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> todos();
	Restaurante porId(Long id);
	Restaurante adicionar(Restaurante restaurante);
	void remover(Restaurante restaurante);

}
