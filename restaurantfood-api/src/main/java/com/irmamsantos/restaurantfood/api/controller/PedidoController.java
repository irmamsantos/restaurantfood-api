package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.api.assembler.PedidoDTOAssembler;
import com.irmamsantos.restaurantfood.api.assembler.PedidoResumoDTOAssembler;
import com.irmamsantos.restaurantfood.api.model.dto.output.PedidoDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.PedidoResumoDTO;
import com.irmamsantos.restaurantfood.domain.exception.PedidoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.repository.PedidoRepository;
import com.irmamsantos.restaurantfood.domain.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoDTOAssembler pedidoDTOAssembler;
	
	@Autowired
	private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;	
	
	@Autowired
	private PedidoRepository pedidoRepository; 
	
	@Autowired
	private PedidoService pedidoService; 
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PedidoResumoDTO> listar() {
		return pedidoResumoDTOAssembler.toCollectionDTO(pedidoRepository.findAll());
	}
	
	@GetMapping("/{cidadeId}")
	public PedidoDTO buscar(@PathVariable("cidadeId") Long id) 
			throws PedidoNaoEncontradoException {
		
		return pedidoDTOAssembler.toDTO(pedidoService.buscarOuFalhar(id));
	}
}
