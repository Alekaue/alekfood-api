package com.alekaue.alekfood.di.notificador;

import org.springframework.stereotype.Component;

import com.alekaue.alekfood.di.modelo.Cliente;

@TipoDoNotificador(valeu = NivelUrgencia.NORMAL)
@Component
public class NotificadorSMS implements Notificador {

	
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("Notificando %s por SMS através do telefone %s:  %s\n", cliente.getNome(),
				cliente.getTelefone(), mensagem);
	}

}
