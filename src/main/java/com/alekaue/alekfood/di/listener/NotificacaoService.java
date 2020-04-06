package com.alekaue.alekfood.di.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.alekaue.alekfood.di.notificador.NivelUrgencia;
import com.alekaue.alekfood.di.notificador.Notificador;
import com.alekaue.alekfood.di.notificador.TipoDoNotificador;
import com.alekaue.alekfood.di.service.ClienteAtivadoEvent;

@Component
public class NotificacaoService {
	
	@TipoDoNotificador(valeu = NivelUrgencia.URGENTE)
	@Autowired
	private Notificador notificador;
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo.");
	}
			
}
