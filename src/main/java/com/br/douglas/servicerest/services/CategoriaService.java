package com.br.douglas.servicerest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.douglas.servicerest.domain.Categoria;
import com.br.douglas.servicerest.repositories.CategoriaRepository;
import com.br.douglas.servicerest.services.exceptions.DataIntegrityException;
import com.br.douglas.servicerest.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Categoria insert(Categoria obj) {
		return repo.saveAndFlush(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.saveAndFlush(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que contem produtod.");
		}
	}
}