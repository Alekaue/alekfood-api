package com.alekaue.alekfood.domain.repository;

import java.util.List;

import com.alekaue.alekfood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	List<FormaPagamento> todas();
	FormaPagamento porId(Long id);
	FormaPagamento adicionar(FormaPagamento formaDePagamento);
	void remover(FormaPagamento formaDePagamento);
}
