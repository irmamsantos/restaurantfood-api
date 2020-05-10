package com.irmamsantos.restaurantfood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {
	
	/*
	 * Não achou adequado definir como parametro um MultipartFile por ser especifico da web/http,
	 * ficava acoplado, preferiu algo mais reutilizavel,
	 * então definiu uma nova classe (em vez de usar File)
	 */
	void armazenar(NovaFoto novaFoto);
	
	void remover(String nomeArquivo);
	
	InputStream recuperar(String nomeArquivo);
	
	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID() + "_" + nomeOriginal;
	}

	default void subtituir(String nomeArquivoExistente, NovaFoto novaFoto) {
		if (nomeArquivoExistente != null) {
			remover(nomeArquivoExistente);
		}
		armazenar(novaFoto);
	}
	
	@Builder
	@Getter	
	class NovaFoto {
		private String nomeArquivo;
		private String contentType;
		private InputStream inputStream;
	}
}
