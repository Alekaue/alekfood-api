package com.alekaue.alekfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alekaue.alekfood.api.assember.PedidoInputDisassembler;
import com.alekaue.alekfood.api.assember.PedidoModelAssembler;
import com.alekaue.alekfood.api.assember.PedidoResumoModelAssembler;
import com.alekaue.alekfood.api.model.PedidoModel;
import com.alekaue.alekfood.api.model.PedidoResumoModel;
import com.alekaue.alekfood.api.model.input.PedidoInput;
import com.alekaue.alekfood.domain.exception.EntidadeNaoEncontradaException;
import com.alekaue.alekfood.domain.exception.NegocioException;
import com.alekaue.alekfood.domain.model.Pedido;
import com.alekaue.alekfood.domain.model.Usuario;
import com.alekaue.alekfood.domain.repository.PedidoRepository;
import com.alekaue.alekfood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	
	@GetMapping
	public List<PedidoResumoModel> listar() {
		List<Pedido> todosPedidos = pedidoRepository.findAll();
		return pedidoResumoModelAssembler.toCollection(todosPedidos);
	}
	
	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
		
		return pedidoModelAssembler.toModel(pedido);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
			
			//TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);
			
			novoPedido = emissaoPedido.emitir(novoPedido);
			
			return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}
	
}
