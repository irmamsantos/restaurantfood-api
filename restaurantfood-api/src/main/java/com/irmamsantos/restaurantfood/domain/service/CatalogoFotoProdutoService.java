package com.irmamsantos.restaurantfood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.irmamsantos.restaurantfood.domain.exception.FotoProdutoNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.model.FotoProduto;
import com.irmamsantos.restaurantfood.domain.repository.ProdutoRepository;
import com.irmamsantos.restaurantfood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
		//exluir foto, se existir (senão dava uma SQL constraint Exception, decidiu contornar desta forma)
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String nomeArquivoExistente = null;
		
		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
		String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
		
		if (fotoExistente.isPresent()) {
			//excluir a foto da BD
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}
		
		/*
		Fez primeiro o save para caso dê erro na bd não armazenar foto 
		faz flush explicitamente para ser antes do armazenar
		mas só faz commit no final do método, se der erro no armazenamento faz rollback
		*/
		foto.setNomeArquivo(nomeNovoArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(foto.getNomeArquivo())
				.contentType(foto.getContentType())
				.inputStream(dadosArquivo).build();
		
		fotoStorageService.subtituir(nomeArquivoExistente, novaFoto);
		
		return foto;
	}
	
	public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) throws FotoProdutoNaoEncontradaException {
		return produtoRepository.findFotoById(restauranteId, produtoId)
				.orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
	}

	@Transactional
	public void apagar(Long restauranteId, Long produtoId) {
		FotoProduto fotoExistente = buscarOuFalhar(restauranteId, produtoId);
		
		produtoRepository.delete(fotoExistente);
		produtoRepository.flush();
		
		fotoStorageService.remover(fotoExistente.getNomeArquivo());
	}
}
