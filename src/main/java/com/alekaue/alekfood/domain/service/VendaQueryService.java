package com.alekaue.alekfood.domain.service;

import java.util.List;

import com.alekaue.alekfood.domain.filter.VendaDiariaFilter;
import com.alekaue.alekfood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);
}
