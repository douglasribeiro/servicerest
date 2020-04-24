package com.br.douglas.servicerest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.douglas.servicerest.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	@Query("SELECT c FROM Cidade c WHERE c.id = ?1")
    public Cidade findCodigo(Integer codigo);
	
}
