package com.irmamsantos.restaurantfood.core.email;

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
@ConfigurationProperties("restaurantfood.email")
public class EmailProperties {
	
	@NotNull
	private String remetente;
	
	// Atribuimos FAKE como padrão
	// Isso evita o problema de enviar e-mails de verdade caso você esqueça
	// de definir a propriedade
	private Implementacao impl = Implementacao.FAKE;
	
	enum Implementacao {
		SMTP,
		FAKE
	}
}
