package com.irmamsantos.restaurantfood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	PARAMETRO_INVALIDO("parametro-invalido", "Parametro inválido"),
	MENSAGEM_IMCOMPREENSIVEL("/mensagem-imcompreensivel", "Mensagem imcompreensível"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://restaurantfood.com" + path;
		this.title = title;
	}
}
