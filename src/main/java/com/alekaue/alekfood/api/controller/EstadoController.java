package com.alekaue.alekfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alekaue.alekfood.api.assember.EstadoInputDisassembler;
import com.alekaue.alekfood.api.assember.EstadoModelAssembler;
import com.alekaue.alekfood.api.model.EstadoModel;
import com.alekaue.alekfood.api.model.input.EstadoInput;
import com.alekaue.alekfood.domain.model.Estado;
import com.alekaue.alekfood.domain.repository.EstadoRepository;
import com.alekaue.alekfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;
	
	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;
	
	@GetMapping
	public List<EstadoModel> listar() {
		List<Estado> todosEstados = estadoRepository.findAll();
		
		return estadoModelAssembler.toCollectionModel(todosEstados);
	}
	
	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
		
		return estadoModelAssembler.toModel(estado);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		
		estado = cadastroEstado.salvar(estado);
		
		return estadoModelAssembler.toModel(estado);
	}
	
	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId, 
			@RequestBody @Valid EstadoInput estadoInput) {
		
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
		
		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
		
		//BeanUtils.copyProperties(estado, estadoAtual, "id");

		estadoAtual = cadastroEstado.salvar(estadoAtual);
		
		
		return estadoModelAssembler.toModel(estadoAtual);
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
			cadastroEstado.excluir(estadoId);
	}

}
