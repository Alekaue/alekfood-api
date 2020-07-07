package com.alekaue.alekfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<VendaDiaria> consultaVendasDiarias(VendaDiariaFilter filtro, 
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
		return vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
	}
}
