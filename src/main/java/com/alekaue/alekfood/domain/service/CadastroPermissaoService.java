package com.alekaue.alekfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alekaue.alekfood.domain.exception.PermissaoNaoEncotradaException;
import com.alekaue.alekfood.domain.model.Permissao;
import com.alekaue.alekfood.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncotradaException(permissaoId));
	}
}
