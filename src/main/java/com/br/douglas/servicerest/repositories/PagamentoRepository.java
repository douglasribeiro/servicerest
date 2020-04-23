package com.br.douglas.servicerest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.douglas.servicerest.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
