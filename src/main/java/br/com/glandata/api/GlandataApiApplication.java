package br.com.glandata.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "API Produtos", version = "1.0", description = "API de Produtos da Glan Data"))
public class GlandataApiApplication implements CommandLineRunner {
	
	@Value("${mensagem}")
	private String mensagem;
	
	public static void main(String[] args) {
		SpringApplication.run(GlandataApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(mensagem);
	}

}
