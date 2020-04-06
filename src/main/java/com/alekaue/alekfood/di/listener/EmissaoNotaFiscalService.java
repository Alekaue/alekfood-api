package com.alekaue.alekfood.di.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.alekaue.alekfood.di.service.ClienteAtivadoEvent;

@Component
public class EmissaoNotaFiscalService {
	

	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		System.out.println("Emitindo nota fiscal para o cliente" + event.getCliente().getNome());
	}
}
