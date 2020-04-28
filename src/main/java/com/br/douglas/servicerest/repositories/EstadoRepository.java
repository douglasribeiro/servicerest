package com.br.douglas.servicerest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.br.douglas.servicerest.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	@Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNome();
}
