package com.irmamsantos.restaurantfood.domain.service;

import java.util.Map;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

public interface EnvioEmailService {
	
	void enviar(Mensagem mensagem);

	@Getter
	@Builder
	class Mensagem {
		
		/*
		 * lombok - Permite inserir um de cada vez
		 */
		@Singular
		private Set<String> destinatarios;
		/*
		 * lombok
		 */
		@NonNull
		private String assunto;

		/*
		 * lombok
		 */
		@NonNull
		private String corpo;
		
		/*
		 * lombok - Permite inserir um de cada vez, indica o nome usado em singular
		 */
		@Singular("variavel")
		private Map<String, Object> variaveis;
	}
}
