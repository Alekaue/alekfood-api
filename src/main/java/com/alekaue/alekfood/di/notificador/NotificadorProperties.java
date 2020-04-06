package com.alekaue.alekfood.di.notificador;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("notificador.email")
@Component
public class NotificadorProperties {


	/**
	 * Host do servidor de e-mail
	 */
	private String hostServidor;
	
	/**
	 * Porta do servidor de e-mail
	 */
	private Integer portaServidor;

	public String getHostServidor() {
		return hostServidor;
	}

	public void setHostServidor(String hostServidor) {
		this.hostServidor = hostServidor;
	}

	public Integer getPortaServidor() {
		return portaServidor;
	}

	public void setPortaServidor(Integer portaServidor) {
		this.portaServidor = portaServidor;
	}
	

}
