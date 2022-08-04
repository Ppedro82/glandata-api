package br.com.glandata.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.glandata.api.model.CategoriaModel;
import br.com.glandata.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository categoriaRepository;
	
	public List<CategoriaModel> listarTodos() {
		return categoriaRepository.findAll();
	}
	
	public Optional<CategoriaModel> buscarPorId(Long idCategoria) {
		return categoriaRepository.findById(idCategoria);
	}
	
	public CategoriaModel inserir(CategoriaModel categoriaModel) {
		return categoriaRepository.save(categoriaModel);
	}
	
	public Optional<CategoriaModel> atualizar(Long idCategoria, CategoriaModel categoriaModel) {
		return this.buscarPorId(idCategoria)
				.map(c -> {
					c.setNome(categoriaModel.getNome());
					c.setProdutos(categoriaModel.getProdutos());
					c.setDescricao(categoriaModel.getDescricao());
					return categoriaRepository.save(c);
				});
	}
	
	public Boolean deletar(Long idCategoria) {
		return this.buscarPorId(idCategoria)
				.map(c -> {
					categoriaRepository.delete(c);
					return true;
				}).orElse(true);
	}
	
}
