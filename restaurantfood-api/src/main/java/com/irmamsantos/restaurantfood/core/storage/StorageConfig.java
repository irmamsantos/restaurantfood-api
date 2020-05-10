package com.irmamsantos.restaurantfood.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.irmamsantos.restaurantfood.core.storage.StorageProperties.TipoStorage;
import com.irmamsantos.restaurantfood.domain.service.FotoStorageService;
import com.irmamsantos.restaurantfood.infrastructure.service.storage.LocalFotoStorageService;
import com.irmamsantos.restaurantfood.infrastructure.service.storage.S3FotoStorageService;

@Configuration
public class StorageConfig {
	
	@Autowired
	private StorageProperties storageProperties;

	/*
	 * @ConditionalOnProperty para não instanciar este bean caso no properties esteja configurado LOCAL
	 */
	@Bean
	@ConditionalOnProperty(name = "restaurantfood.storage.tipo", havingValue = "s3")
	public AmazonS3 amazonS3() {
		
		BasicAWSCredentials credentials = new BasicAWSCredentials(
				storageProperties.getS3().getIdChaveAcesso()
				, storageProperties.getS3().getChaveAcessoSecreta());
		
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(storageProperties.getS3().getRegiao())
				.build();
	}
	
	/*
	 * Comentou a anotação de service em cada implementação de FotoStorageService que permite o Spring
	 * injectar os objectos, mas tinha que alternar comentando no código um ou outra implementação
	 * Definiu em proprieties qual a implementação escolhida para cada ambiente e através da anotação 
	 * bean o Spring depois sabe injectar o objecto com a respectiva implementação
	 */
	@Bean
	public FotoStorageService fotoStorageService() {
		if (TipoStorage.S3.equals(storageProperties.getTipo())) {
			return new S3FotoStorageService();
		} else {
			return new LocalFotoStorageService();
		}
	}
}
