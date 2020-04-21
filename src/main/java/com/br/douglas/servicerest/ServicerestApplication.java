package com.br.douglas.servicerest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.douglas.servicerest.domain.Categoria;
import com.br.douglas.servicerest.repositories.CategoriaRepository;

@SpringBootApplication
public class ServicerestApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ServicerestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Escritório");
		Categoria cat2 = new Categoria(null,"Informática");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	}

}
