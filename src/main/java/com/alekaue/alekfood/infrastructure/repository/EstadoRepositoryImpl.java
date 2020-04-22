package com.alekaue.alekfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alekaue.alekfood.domain.model.Estado;
import com.alekaue.alekfood.domain.repository.EstadoRepository;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Estado> todos() {
		return manager.createQuery("from Estado", Estado.class)
				.getResultList();
	}

	@Override
	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}

	@Transactional
	@Override
	public Estado adicionar(Estado estado) {
		return manager.merge(estado);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Estado estado = porId(id);
		
		if (estado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(estado);
	}
	
	
}
