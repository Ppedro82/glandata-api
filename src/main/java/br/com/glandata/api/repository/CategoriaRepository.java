package br.com.glandata.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.glandata.api.model.CategoriaModel;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {

}
