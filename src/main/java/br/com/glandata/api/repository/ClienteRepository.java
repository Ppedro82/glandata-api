package br.com.glandata.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.glandata.api.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

}
