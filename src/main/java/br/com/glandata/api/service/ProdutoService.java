package br.com.glandata.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.glandata.api.model.ProdutoModel;
import br.com.glandata.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoModel> listarTodos() {
		return produtoRepository.findAll();
	}
	
	public Optional<ProdutoModel> buscarPorId(Long idProduto) {
		return produtoRepository.findById(idProduto);
	}
	
	public Optional<ProdutoModel> atualizar(Long id, ProdutoModel produtoModel) {
		return this.buscarPorId(id)
				.map(p -> {
					p.setNome(produtoModel.getNome());
					p.setCategoria(produtoModel.getCategoria());
					p.setDataCadastro(produtoModel.getDataCadastro());
					p.setDescricao(produtoModel.getDescricao());
					p.setPreco(produtoModel.getPreco());
					return produtoRepository.save(p);
		});
	}
	
	public ProdutoModel adicionar(ProdutoModel produtoModel) {
		return produtoRepository.save(produtoModel);
	}
	
	public Boolean deletar(Long idProduto) {
		return this.buscarPorId(idProduto)
				.map(p -> {	
				produtoRepository.delete(p);
				return true;
				}
				).orElse(true);
	
}

}