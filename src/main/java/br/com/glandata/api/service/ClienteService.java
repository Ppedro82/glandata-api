package br.com.glandata.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.glandata.api.model.ClienteModel;
import br.com.glandata.api.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<ClienteModel> listarTodos() {
		return clienteRepository.findAll();
	}
	
	public Optional<ClienteModel> buscarPorId(Long idCliente) {
		return clienteRepository.findById(idCliente);
	}

	public Optional<ClienteModel> atualizar(Long idCliente, ClienteModel clienteModel) {
		return this.buscarPorId(idCliente)
				.map(c -> {
					c.setNome(clienteModel.getNome());
					c.setCpf(clienteModel.getNome());
					c.setDataNascimento(clienteModel.getDataNascimento());
					return clienteRepository.save(c);
				});
		
	}

	public ClienteModel adicionar(ClienteModel clienteModel) {
		return clienteRepository.save(clienteModel);
	}

	public Boolean deletar(Long idCliente) {
		return this.buscarPorId(idCliente).map(c -> {
			clienteRepository.delete(c);
			return true;
		}
		).orElse(true);
	}
	
}
