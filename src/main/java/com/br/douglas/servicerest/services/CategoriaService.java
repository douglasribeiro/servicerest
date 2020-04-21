package com.br.douglas.servicerest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.douglas.servicerest.domain.Categoria;
import com.br.douglas.servicerest.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Optional<Categoria> buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return Optional.ofNullable(obj.orElse(null));	
	}
}
