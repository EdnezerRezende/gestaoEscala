package br.com.gestaoescala.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestaoescala.domain.Cidade;
import br.com.gestaoescala.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeDao;
	
	public List<Cidade> findByEstado(Integer estadoId) {
		return cidadeDao.findCidades(estadoId);
	}
	
	
	
}
