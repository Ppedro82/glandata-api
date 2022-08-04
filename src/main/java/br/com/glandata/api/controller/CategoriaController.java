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

import br.com.glandata.api.model.CategoriaModel;
import br.com.glandata.api.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	public CategoriaService categoriaService;
	
	@GetMapping("")
	public ResponseEntity<List<CategoriaModel>> listarTodos() {
		return ResponseEntity.ok(categoriaService.listarTodos());
	}
	
	@GetMapping("/{idCategoria}")
	public ResponseEntity<CategoriaModel> buscarPorId(@PathVariable Long idCategoria) {
		Optional<CategoriaModel> categoria = categoriaService.buscarPorId(idCategoria);
		if(categoria.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		CategoriaModel categoria2 = categoria.get();
		return ResponseEntity.ok(categoria2);
	}
	
	@PostMapping("")
	public ResponseEntity<CategoriaModel> inserir(@RequestBody @Valid CategoriaModel categoriaModel) {
		return ResponseEntity.ok(categoriaService.inserir(categoriaModel));
	}
	
	@PutMapping("/{idCategoria}")
	public ResponseEntity<CategoriaModel> atualizar(@PathVariable Long idCategoria, @RequestBody CategoriaModel categoriaModel) {
		return categoriaService.atualizar(idCategoria, categoriaModel).map(c -> ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{idCategoria}")
	public ResponseEntity<CategoriaModel> deletar(@PathVariable Long idCategoria) {
		return categoriaService.deletar(idCategoria) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
}
