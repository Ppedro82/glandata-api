package br.com.glandata.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.glandata.api.model.ProdutoModel;
import br.com.glandata.api.service.ProdutoService;
//import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos",  description = "Gerenciamento dos Produtos da API")
//@Hidden //Oculta os endpoints e schemas da Documentação gerada pelo Swagger
//@SecurityScheme(name = "Authorization", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@SecurityRequirement(name = "Authorization")
@ApiResponses(value = {
		@ApiResponse (responseCode = "404", description = "O produto informado não existe", content = @Content),
		@ApiResponse (responseCode = "400", description = "Dados Inválidos", content = @Content)
})
public class ProdutoController {
	
	@Autowired
	public ProdutoService produtoService;

	@GetMapping("")
	public ResponseEntity<List<ProdutoModel>> listarTodos() {
		return ResponseEntity.ok(produtoService.listarTodos());
	}
	
	
	@GetMapping("/{idProduto}")
	@Operation(summary = "Busca um produto por ID", security = @SecurityRequirement(name = "Authorization"))
	public ResponseEntity<ProdutoModel> buscarPorId(@PathVariable Long idProduto) {
		
		Optional<ProdutoModel> produto = produtoService.buscarPorId(idProduto);		
		
		if(produto.isEmpty()) {
			return ResponseEntity.notFound().build();
		
		}
		ProdutoModel produto2 = produto.get();
		
		return ResponseEntity.ok(produto2);
	}
	
	@PostMapping("")
	public ResponseEntity<ProdutoModel> inserir(@RequestBody @Valid ProdutoModel produtoModel) {
		return ResponseEntity.ok(produtoService.adicionar(produtoModel));
	}
	
	@PutMapping("/{idProduto}")
	public ResponseEntity<ProdutoModel> atualizar(@PathVariable Long idProduto, @RequestBody ProdutoModel produtoModel) {
		return produtoService.atualizar(idProduto, produtoModel)
				.map(p -> ResponseEntity.ok(p)).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{idProduto}")
	public ResponseEntity<ProdutoModel> deletar(@PathVariable Long idProduto) {
		return produtoService.deletar(idProduto) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
}
