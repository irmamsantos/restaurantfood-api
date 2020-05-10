package com.irmamsantos.restaurantfood.infrastructure.service.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.irmamsantos.restaurantfood.core.storage.StorageProperties;
import com.irmamsantos.restaurantfood.domain.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService {
	
	@Autowired
	private StorageProperties storageProperties;
	
//	@Value("${restaurantfood.storage.local.directorio-fotos}")
//	private Path directorioFotos;

	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
			FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			new StorageException("Não foi possível armazenar arquivo.", e);
		}
	}
	
	@Override
	public void remover(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			Files.deleteIfExists(arquivoPath);
		} catch (Exception e) {
			new StorageException("Não foi possível remover arquivo.", e);
		}
	}	
	
	//recuperar no sentido de obter
	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		try {
	        Path arquivoPath = getArquivoPath(nomeArquivo);
	        
	        FotoRecuperada fotoRecuperada = FotoRecuperada
	        		.builder()
	        		.inputStream(Files.newInputStream(arquivoPath))
	        		.build();

	        return fotoRecuperada;
	    } catch (Exception e) {
	        throw new StorageException("Não foi possível recuperar arquivo.", e);
	    }
	}	
	
	private Path getArquivoPath(String nomeArquivo) {
		//return directorioFotos.resolve(Path.of(nomeArquivo));
		return storageProperties.getLocal().getDirectorioFotos().resolve(Path.of(nomeArquivo));
	}
}
