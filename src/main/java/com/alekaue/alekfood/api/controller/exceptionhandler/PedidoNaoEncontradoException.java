package com.alekaue.alekfood.api.controller.exceptionhandler;

import com.alekaue.alekfood.domain.exception.EntidadeNaoEncontradaException;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;


	public PedidoNaoEncontradoException(String codidoPedido) {
		super(String.format("Não existe um pedido com o código %s", codidoPedido));
	}
}
