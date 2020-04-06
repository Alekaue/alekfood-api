package com.alekaue.alekfood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.alekaue.alekfood.di.modelo.Cliente;

@Component
public class AtivacaoClienteService {

	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	/*
	 * @PostConstruct public void init() { System.out.println("init"); }
	 * 
	 * @PreDestroy public void destroy() { System.out.println("destroy"); }
	 */
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
		
	}

}
