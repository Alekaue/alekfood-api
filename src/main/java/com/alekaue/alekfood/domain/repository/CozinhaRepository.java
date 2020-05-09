package com.alekaue.alekfood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alekaue.alekfood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long>{
	
	List<Cozinha> findTodasByNome(String nome);
	
	List<Cozinha> findTodasByNomeContaining(String nome);
	
}
