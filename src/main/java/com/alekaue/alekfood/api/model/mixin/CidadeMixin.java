package com.alekaue.alekfood.api.model.mixin;

import com.alekaue.alekfood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CidadeMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Estado estado;
}
