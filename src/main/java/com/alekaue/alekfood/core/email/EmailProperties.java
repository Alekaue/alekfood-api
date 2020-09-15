package com.alekaue.alekfood.core.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("alekfood.email")
public class EmailProperties {
	
	private Sandbox sandBox = new Sandbox();
	
	private Implementacao impl = Implementacao.FAKE;

	@NotNull
	private String remetente;
	
	public enum Implementacao {
		SANDBOX, SMTP, FAKE
	}
	
	@Getter
	@Setter
	public class Sandbox {
		private String destinatario;
	}
	
}

