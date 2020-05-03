package com.irmamsantos.restaurantfood.domain.service;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {
	
	/*
	 * Não achou adequado definir como parametro um MultipartFile por ser especifico da web/http,
	 * ficava acoplado, preferiu algo mais reutilizavel,
	 * então definiu uma nova classe (em vez de usar File)
	 */
	void armazenar(NovaFoto novaFoto);
	
	@Builder
	@Getter	
	class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;
	}
}
