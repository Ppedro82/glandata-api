package br.com.glandata.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.glandata.api.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>  {

}
