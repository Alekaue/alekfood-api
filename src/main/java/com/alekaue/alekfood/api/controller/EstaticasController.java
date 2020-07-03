package com.alekaue.alekfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alekaue.alekfood.domain.filter.VendaDiariaFilter;
import com.alekaue.alekfood.domain.model.dto.VendaDiaria;
import com.alekaue.alekfood.domain.service.VendaQueryService;

@RestController
@RequestMapping("/estatisticas")
public class EstaticasController {

	@Autowired
	private VendaQueryService vendaQueryService;
	
	@GetMapping("/vendas-diarias")
	public List<VendaDiaria> consultaVendasDiarias(VendaDiariaFilter filtro) {
		return vendaQueryService.consultarVendasDiarias(filtro);
	}
}
