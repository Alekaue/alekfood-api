package com.alekaue.alekfood.api.controller.exceptionhandler;

import lombok.Getter;


@Getter
public enum ProblemType {

	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADA("/recurso-não-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	DADOS_INVALIDOS("/dados-invalido", "Dados inválidos");
	
	
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title){
		this.uri = "https://alekfood.com.br" + path;
		this.title = title;
	}
}
