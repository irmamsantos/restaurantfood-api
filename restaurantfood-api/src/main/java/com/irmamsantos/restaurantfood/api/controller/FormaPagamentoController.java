package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.api.assembler.FormaPagamentoDTOAssembler;
import com.irmamsantos.restaurantfood.api.assembler.FormaPagamentoInputDTODisassembler;
import com.irmamsantos.restaurantfood.api.model.dto.input.FormaPagamentoInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.FormaPagamentoDTO;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.FormaPagamentoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.exception.NegocioException;
import com.irmamsantos.restaurantfood.domain.model.FormaPagamento;
import com.irmamsantos.restaurantfood.domain.repository.FormaPagamentoRepository;
import com.irmamsantos.restaurantfood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formaPagamentos")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository; 
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;
	
	@Autowired
	private FormaPagamentoInputDTODisassembler formaPagamentoInputDTODisassembler;	
	
	@GetMapping
	public List<FormaPagamentoDTO> listar() {
		return formaPagamentoDTOAssembler.toCollectionDTO(formaPagamentoRepository.findAll());
	}
	
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoDTO buscar(@PathVariable("formaPagamentoId") Long id) 
			throws EntidadeNaoEncontradaException {
		
		return formaPagamentoDTOAssembler.toDTO(formaPagamentoService.buscarOuFalhar(id));
	}
	
	@ResponseStatus(code=HttpStatus.CREATED)
	@PostMapping
	public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputDTODisassembler.toDomainObject(formaPagamentoInput);
	
		return formaPagamentoDTOAssembler.toDTO(formaPagamentoService.salvar(formaPagamento));
	}
	
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoDTO actualizar(@PathVariable Long formaPagamentoId, 
				@RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInput) 
			throws EntidadeNaoEncontradaException, NegocioException {
		try {
			FormaPagamento formaPagamentoActual = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

			formaPagamentoInputDTODisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoActual);

			return formaPagamentoDTOAssembler.toDTO(formaPagamentoService.salvar(formaPagamentoActual));
			
		} catch (FormaPagamentoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	} 
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) 
			throws EntidadeNaoEncontradaException, EntidadeEmUsoException {
		
		formaPagamentoService.excluir(formaPagamentoId);
	}	
}
