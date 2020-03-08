package com.irmamsantos.restaurantfood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {
	
	@LocalServerPort
	private int portParam;
	
	@Autowired
	private Flyway flyway;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = portParam;
		RestAssured.basePath = "/cozinhas";
		
		//carrega/reset os dados do ficheiro db\testdata\afterMigrate.sql em cada teste
		//definido nesta classe
		flyway.migrate();
	}

	//Exemplos de testes de API
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveConter4Cozinhas_QuandoConsultarCozinhas() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(4))
			.body("nome", hasItems("Indiana", "Tailandesa"));
	}
	
	@Test
	public void testRetornarStatus201_QuandoCadastrarCozinha() {
		
		given()
			.body("{ \"nome\" : \"Chinesa\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}	
	
/* Exemplos de Testes de Integração
  	
	@Autowired
	private CozinhaService cadastroCozinha;

	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorrectos() {
		//cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		//acção
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
		
		//validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		//cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);
		
		//acção
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
		
		//validação
		//excepção
	}
	
	@Test(expected = EntidadeEmUsoException.class)
	public void deveFalhar_QuandoExcluirCozinhaEmUso() 
			throws CozinhaNaoEncontradaException, EntidadeEmUsoException {
		
		cadastroCozinha.excluir(1L);
	}
	
	@Test(expected = CozinhaNaoEncontradaException.class)
	public void deveFalhar_QuandoExcluirCozinhaInexistente() 
			throws CozinhaNaoEncontradaException, EntidadeEmUsoException {
		
		cadastroCozinha.excluir(100L);
	}
*/	
}
