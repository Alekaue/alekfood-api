package com.alekaue.alekfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alekaue.alekfood.domain.model.FormaPagamento;
import com.alekaue.alekfood.domain.repository.FormaPagamentoRepository;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<FormaPagamento> todas() {
		return manager.createQuery("form FormaDePagamento", FormaPagamento.class)
				.getResultList();
	}

	@Override
	public FormaPagamento porId(Long id) {
		return manager.find(FormaPagamento.class, id);
	}

	@Transactional
	@Override
	public FormaPagamento adicionar(FormaPagamento formaDePagamento) {
		return manager.merge(formaDePagamento);
	}

	@Transactional
	@Override
	public void remover(FormaPagamento formaDePagamento) {
		formaDePagamento = porId(formaDePagamento.getId());
		manager.remove(formaDePagamento);
	}

}
