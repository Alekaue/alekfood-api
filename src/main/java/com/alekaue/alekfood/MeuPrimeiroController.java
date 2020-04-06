package com.alekaue.alekfood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alekaue.alekfood.di.modelo.Cliente;
import com.alekaue.alekfood.di.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {
	
	
	private AtivacaoClienteService ativacaoClienteService;
	
	
	
	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		
		Cliente joao = new Cliente("João", "joao@xyz.com", "21314231431");
		
		ativacaoClienteService.ativar(joao);
		
		return "Hello!";
	}

}
