package br.com.gestaoescala.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestaoescala.domain.Servidor;
import br.com.gestaoescala.repositories.ServidorRepository;

@Service
public class ServidorService {

	@Autowired
	private ServidorRepository servidorDao;
	
	public Servidor buscar(Integer id) {
		Optional<Servidor> servidor = servidorDao.findById(id);
		return servidor.orElseThrow(()-> 
			new br.com.gestaoescala.services.exceptions.ObjectNotFoundException(
					"Objeto não encontrado! id: " + id + ", tipo:"+Servidor.class.getName()));
	}
}
