package com.alekaue.alekfood.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.alekaue.alekfood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class CozinhaMixin {
	
	@JsonIgnore
	private List<Restaurante> restaurantes = new ArrayList<>();

}