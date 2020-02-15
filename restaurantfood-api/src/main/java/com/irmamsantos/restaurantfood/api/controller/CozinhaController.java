package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.api.model.CozinhasXmlWrapper;
import com.irmamsantos.restaurantfood.domain.model.Cozinha;
import com.irmamsantos.restaurantfood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping(value = "/cozinhas") //, produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository; 
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar1() {
		return cozinhaRepository.todas();
	}
	
	@GetMapping(produces=MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXML() {
		return new CozinhasXmlWrapper(listar1());
	}
	
	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
		return cozinhaRepository.porId(id);
	}
	
}
