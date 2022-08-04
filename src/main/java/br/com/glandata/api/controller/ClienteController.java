package br.com.glandata.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

//import org.modelmapper.ModelMapper;
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

import br.com.glandata.api.model.ClienteModel;
import br.com.glandata.api.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

//	@Autowired
//	private ModelMapper modelMapper;

	/**
	 * Lista todos os clientes
	 */
	@GetMapping("")
	public ResponseEntity<List<ClienteModel>> listarTodos() {
		return ResponseEntity.ok(clienteService.listarTodos());
	}
	
	/**
	 * Insere novos dados 
	 */
	@PostMapping("")
	public ResponseEntity<ClienteModel> inserir (@RequestBody @Valid ClienteModel clienteModel) {
		return ResponseEntity.ok(clienteService.adicionar(clienteModel));
	}
	
	/**
	 * Busca um cliente por ID
	 */
	@GetMapping("/{idCliente}")
	public ResponseEntity<ClienteModel> buscarPorId(@PathVariable Long idCliente) {
		
		Optional<ClienteModel> cliente = clienteService.buscarPorId(idCliente);
		
		if(cliente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		ClienteModel client = cliente.get();
		return ResponseEntity.ok(client);
	}
	
	/*
	 * Atualizar os dados
	 */
	@PutMapping("/{idCliente}")
	public ResponseEntity<ClienteModel> atualizar(@PathVariable Long idCliente, @RequestBody  ClienteModel clienteModel) {
		return clienteService.atualizar(idCliente, clienteModel)
				.map(c -> ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());
	}
	
	/*
	 * Deleter cliente
	 */
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<?> deletarCliente(@PathVariable Long idCliente) {
		return clienteService.deletar(idCliente) ? 
				ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
}
