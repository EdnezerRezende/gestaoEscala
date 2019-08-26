package br.com.gestaoescala.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestaoescala.domain.Cidade;
import br.com.gestaoescala.domain.Endereco;
import br.com.gestaoescala.domain.Servidor;
import br.com.gestaoescala.dto.ServidorDTO;
import br.com.gestaoescala.dto.ServidorNewDTO;
import br.com.gestaoescala.repositories.EnderecoRepository;
import br.com.gestaoescala.repositories.ServidorRepository;
import br.com.gestaoescala.services.exceptions.DataIntegrityException;
import br.com.gestaoescala.services.exceptions.ObjectNotFoundException;

@Service
public class ServidorService {

	@Autowired
	private ServidorRepository servidorDao;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Servidor buscar(Integer id) {
		Optional<Servidor> servidor = servidorDao.findById(id);
		return servidor.orElseThrow(()-> 
			new br.com.gestaoescala.services.exceptions.ObjectNotFoundException(
					"Objeto não encontrado! id: " + id + ", tipo:"+Servidor.class.getName()));
	}
	
	@Transactional
	public Servidor insert(Servidor obj) {
		obj.setId(null);
		obj = servidorDao.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	
	public Servidor update(Servidor obj) {
		Servidor newObj = buscar(obj.getId());
		updateData(newObj, obj);
		return servidorDao.save(newObj);
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			servidorDao.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	public List<Servidor> findAll() {
		return servidorDao.findAll();
	}
	
	public Servidor findByEmail(String email) {
		Servidor obj = servidorDao.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Tipo: " + Servidor.class.getName());
		}
		return obj;
	}
	
	public Page<Servidor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return servidorDao.findAll(pageRequest);
	}
	
	public Servidor fromDTO(ServidorDTO objDto) {
		return new Servidor(objDto.getId(), objDto.getMatricula(), objDto.getNome(), objDto.getEmail());
	}
	
	public Servidor fromDTO(ServidorNewDTO objDto) {
		Servidor cli = new Servidor(null, objDto.getMatricula(), objDto.getNome(), objDto.getEmail());
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Servidor newObj, Servidor obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	
}
