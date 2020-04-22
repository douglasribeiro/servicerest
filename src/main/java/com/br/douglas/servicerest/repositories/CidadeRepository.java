package com.br.douglas.servicerest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.douglas.servicerest.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
