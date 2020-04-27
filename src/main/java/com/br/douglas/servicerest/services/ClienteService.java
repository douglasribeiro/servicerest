package com.br.douglas.servicerest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.douglas.servicerest.domain.Cidade;
import com.br.douglas.servicerest.domain.Cliente;
import com.br.douglas.servicerest.domain.Endereco;
import com.br.douglas.servicerest.domain.enus.TipoCliente;
import com.br.douglas.servicerest.dto.ClienteDTO;
import com.br.douglas.servicerest.dto.ClienteNewDTO;
import com.br.douglas.servicerest.repositories.CidadeRepository;
import com.br.douglas.servicerest.repositories.ClienteRepository;
import com.br.douglas.servicerest.repositories.EnderecoRepository;
import com.br.douglas.servicerest.services.exceptions.DataIntegrityException;
import com.br.douglas.servicerest.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder pe; 
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.saveAndFlush(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir pois existem entidades relacionadas.");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDto(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null ,null, null);
	}
	
	public Cliente fromDto(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj() , TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()));
		Cidade cid = cidadeRepository.findCodigo(objDTO.getCiadeId());
		Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(endereco);
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
			if (objDTO.getTelefone3() != null) {
				cli.getTelefones().add(objDTO.getTelefone3());
			}
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		repo.saveAndFlush(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
}