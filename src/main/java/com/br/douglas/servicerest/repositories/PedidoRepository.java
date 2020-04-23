package com.br.douglas.servicerest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.douglas.servicerest.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
