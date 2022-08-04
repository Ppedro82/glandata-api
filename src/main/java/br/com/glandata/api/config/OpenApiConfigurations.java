package br.com.glandata.api.config;

import java.awt.print.Pageable;

import javax.servlet.http.HttpServletRequest;

//import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import br.com.glandata.api.validation.ErroDeFormularioDto;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfigurations {

	@Value("${application-description}")
	private String appDescription;
	

	@Value("${application-version}")
	private String appVersion;

	@Bean
	public OpenAPI customOpenAPI() {
		
		SpringDocUtils.getConfig().addResponseTypeToIgnore(Pageable.class);
		SpringDocUtils.getConfig().addResponseTypeToIgnore(Sort.class);
		SpringDocUtils.getConfig().addResponseTypeToIgnore(ErroDeFormularioDto.class);
		SpringDocUtils.getConfig().addResponseTypeToIgnore(HttpServletRequest.class);
		
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("Authorization", 
								new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.info(new Info()
						.title("Produtos API")
						.description(appDescription)
						.version(appVersion)
						);
	}
//	
//	@Bean
//	public GroupedOpenApi produtoApi() {
//		return GroupedOpenApi
//				.builder()
//				.group("Produtos")
//				.pathsToMatch("/produtos/**")
//				.build();
//	}
//	
//	@Bean
//	public GroupedOpenApi categoriaApi() {
//		return GroupedOpenApi
//				.builder()
//				.group("Categorias")
//				.pathsToMatch("/categorias/**")
//				.build();
//	}
//	
//	@Bean
//	public GroupedOpenApi clienteApi() {
//		return GroupedOpenApi
//				.builder()
//				.group("Clientes")
//				.pathsToMatch("/clientes/**")
//				.build();
//	}
//	
//	@Bean
//	public GroupedOpenApi authApi() {
//		return GroupedOpenApi
//				.builder()
//				.group("Authorization")
//				.pathsToMatch("/auth/**")
//				.build();
//	}
	
}
