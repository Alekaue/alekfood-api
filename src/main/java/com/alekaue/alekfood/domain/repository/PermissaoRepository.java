package com.alekaue.alekfood.domain.repository;

import java.util.List;

import com.alekaue.alekfood.domain.model.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> todas();
	Permissao porId(Long id);
	Permissao adicionar(Permissao permissao);
	void remover(Permissao permissao);

}
