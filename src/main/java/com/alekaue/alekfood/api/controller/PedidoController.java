package com.alekaue.alekfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alekaue.alekfood.api.assember.PedidoModelAssembler;
import com.alekaue.alekfood.api.model.PedidoModel;
import com.alekaue.alekfood.domain.model.Pedido;
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
	
	@GetMapping
	public List<PedidoModel> listar() {
		List<Pedido> todosPedidos = pedidoRepository.findAll();
		return pedidoModelAssembler.toCollection(todosPedidos);
	}
	
	@GetMapping("/{pedidoId}")
	public PedidoModel buscar(@PathVariable Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
		
		return pedidoModelAssembler.toModel(pedido);
	}
	
	
}
