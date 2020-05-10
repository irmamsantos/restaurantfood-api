package com.irmamsantos.restaurantfood.infrastructure.service.storage;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.irmamsantos.restaurantfood.core.storage.StorageProperties;
import com.irmamsantos.restaurantfood.domain.service.FotoStorageService;

@Service
public class S3FotoStorageService implements FotoStorageService {

	@Autowired
	private StorageProperties storageProperties;

	@Autowired
	private AmazonS3 amazonS3;

	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());

			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(novaFoto.getContentType());

			PutObjectRequest putObjectRequest = new PutObjectRequest(
					storageProperties.getS3().getBucket(), 
					caminhoArquivo, 
					novaFoto.getInputStream(), 
					objectMetadata
					).withCannedAcl(CannedAccessControlList.PublicRead);
			amazonS3.putObject(putObjectRequest);		
		} catch (Exception e) {
			throw new StorageException("Não foi possível enviar foto para Amazon S3", e);
		}
	}

	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDirectorioFotos(), nomeArquivo);
	}

	@Override
	public void remover(String nomeArquivo) {

	}

	@Override
	public InputStream recuperar(String nomeArquivo) {
		return null;
	}

}
