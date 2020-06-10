package com.alekaue.alekfood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.alekaue.alekfood.domain.model.Cozinha;
import com.alekaue.alekfood.domain.repository.CozinhaRepository;
import com.alekaue.alekfood.util.DatabaseCleaner;
import com.alekaue.alekfood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	private static final int COZINHA_ID_INEXISTENTE = 100;

	private Cozinha cozinhaAmericana;
	private int quantidadeCozinhasCadastradas;
	private String jsonCorretoCozinhaChinesa;

//	@Test
//	public void deveAtribuirId_QuandoCadastrarConzinhaComDadosCorretos() {
//		// cenário
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome("Chinesa");
//		
//		// ação
//		novaCozinha = cadastroCozinha.salvar(novaCozinha); 
//		
//		// validação
//		assertThat(novaCozinha).isNotNull();
//		assertThat(novaCozinha.getId()).isNotNull();
//	}
//	
//	@Test
//	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome(null);
//		
//		assertThrows(ConstraintViolationException.class,
//				()-> {
//				 cadastroCozinha.salvar(novaCozinha);
//				});
//	}
//	
//	@Test
//	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
//		assertThrows(EntidadeEmUsoException.class, 
//				() -> {
//					cadastroCozinha.excluir(1L);
//				});
//	}
//	
//	@Test
//	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
//		assertThrows(CozinhaNaoEncontradaException.class, 
//				() -> {
//					cadastroCozinha.excluir(100L);
//				});
//		
//	}
	
	@LocalServerPort
	private int port;
	
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = (port);
		RestAssured.basePath = ("/cozinhas");
		
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(
				"/json/correto/cozinha-chinesa.json");
		
		databaseCleaner.clearTables();
		prepararDados();
		
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}	
	
	@Test
	public void deveRetornarQuantidadeCorretaDeCozinhas_QuandoConsultarCozinhas() {
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(quantidadeCozinhasCadastradas))
			.body("nome", hasItems("Tailandesa", "Americana"));
	}	
	
	@Test
	public void RetornarStatus201_QuandoCadastrarCozinha() {
		given()
			.body(jsonCorretoCozinhaChinesa)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		given()
			.pathParam("cozinhaId", cozinhaAmericana.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaAmericana.getNome()));
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		given()
			.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void prepararDados() {
	    Cozinha cozinhaTailandesa = new Cozinha();
	    cozinhaTailandesa.setNome("Tailandesa");
	    cozinhaRepository.save(cozinhaTailandesa);

	    cozinhaAmericana = new Cozinha();
	    cozinhaAmericana.setNome("Americana");
	    cozinhaRepository.save(cozinhaAmericana);
	    
	    quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
	}
	
}
