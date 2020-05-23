package com.irmamsantos.restaurantfood.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	//Configuração do cors transversal no projecto
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//para todos os mapeamentos nos controllers
		registry.addMapping("/**")
			//por omissão aceita GET, HEAD, PUT, mas neste caso define-se para todos
			.allowedMethods("*")
			//definição de 
			.allowedOrigins("*")
			//por omissão são 30 minutos
			//.maxAge(10)
			;
	}
}
