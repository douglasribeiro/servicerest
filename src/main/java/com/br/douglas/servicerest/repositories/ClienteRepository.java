package com.br.douglas.servicerest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.douglas.servicerest.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
