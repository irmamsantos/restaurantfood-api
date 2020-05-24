package com.irmamsantos.restaurantfood.client.api;

import org.springframework.web.client.RestClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.irmamsantos.restaurantfood.client.model.Problem;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientApiException extends RuntimeException {

	private static final long serialVersionUID = -2737779628597274985L;
	
	@Getter
	private Problem problem;

	public ClientApiException(String message, RestClientResponseException cause) {
		super(message, cause);
		
		deserializeProblem(cause);
	}
	
	private void deserializeProblem(RestClientResponseException cause) {
		ObjectMapper mapper = new ObjectMapper();
		//no caso do Problem ter definido menos atributos do que aqueles vêm na resposta e 
		//não lançar uma excepção (também poderia acrescentar os atributos em causa no Problem) 
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//deu um erro ao desserializar o OffsetDatetime...
		mapper.registerModule(new JavaTimeModule());
		mapper.findAndRegisterModules();
		
		try {
			this.problem = mapper.readValue(cause.getResponseBodyAsString(), Problem.class);
		} catch (JsonProcessingException e) {
			//não deve lançar uma excepção numa excepção...
			log.warn("Não foi possivel desserializar a resposta em um problema", e);
		}
	}
}
