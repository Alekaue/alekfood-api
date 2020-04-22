package com.alekaue.alekfood.domain.repository;

import java.util.List;

import com.alekaue.alekfood.domain.model.Estado;

public interface EstadoRepository {
	
	List<Estado> todos();
	Estado porId(Long id);
	Estado adicionar(Estado estado);
	void remover(Long id);

}
