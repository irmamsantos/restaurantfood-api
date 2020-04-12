package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.irmamsantos.restaurantfood.api.assembler.PedidoDTOAssembler;
import com.irmamsantos.restaurantfood.api.assembler.PedidoInputDTODisassembler;
import com.irmamsantos.restaurantfood.api.assembler.PedidoResumoDTOAssembler;
import com.irmamsantos.restaurantfood.api.model.dto.input.PedidoInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.PedidoDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.PedidoResumoDTO;
import com.irmamsantos.restaurantfood.domain.exception.EstadoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.exception.NegocioException;
import com.irmamsantos.restaurantfood.domain.exception.PedidoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.model.Pedido;
import com.irmamsantos.restaurantfood.domain.model.Usuario;
import com.irmamsantos.restaurantfood.domain.repository.PedidoRepository;
import com.irmamsantos.restaurantfood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoDTOAssembler pedidoDTOAssembler;
	
	@Autowired
	private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;	
	
	@Autowired
	private PedidoInputDTODisassembler pedidoInputDTODisassembler;
	
	@Autowired
	private PedidoRepository pedidoRepository; 
	
	@Autowired
	private EmissaoPedidoService pedidoService; 
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
		List<Pedido> todosPedidos = pedidoRepository.findAll();
		List<PedidoResumoDTO> pedidosDTO = pedidoResumoDTOAssembler.toCollectionDTO(todosPedidos);
		
		MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosDTO);
		
		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.addFilter("pedidoFilter"
				, SimpleBeanPropertyFilter.serializeAll());
		
		if (StringUtils.isNotBlank(campos)) {
			filterProvider.addFilter("pedidoFilter"
				, SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
		}
		
		pedidosWrapper.setFilters(filterProvider);
		return pedidosWrapper;
	}
	
//	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
//	public List<PedidoResumoDTO> listar() {
//		return pedidoResumoDTOAssembler.toCollectionDTO(pedidoRepository.findAll());
//	}
	
	@GetMapping("/{codigoPedido}")
	public PedidoDTO buscar(@PathVariable String codigoPedido) 
			throws PedidoNaoEncontradoException {
		
		return pedidoDTOAssembler.toDTO(pedidoService.buscarOuFalhar(codigoPedido));
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public PedidoDTO adicionar(@RequestBody @Valid PedidoInputDTO pedidoInput) throws NegocioException {
		try {
			Pedido novoPedido = pedidoInputDTODisassembler.toDomainObject(pedidoInput);
			
			// TODO pegar usuário autenticado
	        novoPedido.setCliente(new Usuario());
	        novoPedido.getCliente().setId(1L);
			
			return pedidoDTOAssembler.toDTO(pedidoService.emitir(novoPedido));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
}
