package br.com.gestaoescala.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestaoescala.domain.Estado;
import br.com.gestaoescala.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoDao;
	
	public List<Estado> buscarTodos() {
		return estadoDao.findAllByOrderByNome();
	}
	
	
	
}
