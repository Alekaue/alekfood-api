package com.alekaue.alekfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public Estado buscar(@PathVariable Long estadoId) {
		return cadastroEstado.buscarOuFalhar(estadoId);
		
//		Optional<Estado> estado = estadoRepository.findById(estadoId);
//		
//		if (estado.isPresent()) {
//			return ResponseEntity.ok(estado.get());
//		}
//		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstado.salvar(estado);
	}
	
	@PutMapping("/{estadoId}")
	public Estado atualizar(@PathVariable Long estadoId, 
			@RequestBody Estado estado) {
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
		
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		
		return cadastroEstado.salvar(estadoAtual);
		
//		Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);
//		
//		if (estadoAtual.isPresent()) {
//			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
//			
//			Estado estadoSalvo = cadastroEstado.salvar(estadoAtual.get());
//			
//			return ResponseEntity.ok(estadoSalvo);
//			
//		}
//		
//		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
			cadastroEstado.excluir(estadoId);
	}
	
	
	
	

}
