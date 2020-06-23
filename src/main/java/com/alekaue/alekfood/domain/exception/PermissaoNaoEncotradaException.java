package com.alekaue.alekfood.domain.exception;

public class PermissaoNaoEncotradaException extends EntidadeNaoEncontradaException{

	
	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncotradaException(String mensagem) {
		super(mensagem);
	}

	public PermissaoNaoEncotradaException(Long permissaoId) {
		this(String.format("Não existe um cadastro de permissão com códifo %d", permissaoId));
	}
}
