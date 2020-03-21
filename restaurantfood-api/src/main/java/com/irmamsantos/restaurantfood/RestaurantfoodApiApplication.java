package com.irmamsantos.restaurantfood;

import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.irmamsantos.restaurantfood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class RestaurantfoodApiApplication {

	public static void main(String[] args) {
		
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		SpringApplication.run(RestaurantfoodApiApplication.class, args);
	}
}
