package com.alekaue.alekfood.api.assember;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alekaue.alekfood.api.model.input.RestauranteInput;
import com.alekaue.alekfood.domain.model.Cozinha;
import com.alekaue.alekfood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		
		//Para evitar org.hibernate.HibernateException: identifier of an instance of 
		//com.alekaue.alekfood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		modelMapper.map(restauranteInput, restaurante);
	}
}
