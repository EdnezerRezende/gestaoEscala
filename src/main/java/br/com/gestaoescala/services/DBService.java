package br.com.gestaoescala.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestaoescala.domain.Servidor;
import br.com.gestaoescala.repositories.ServidorRepository;

@Service
public class DBService {

	@Autowired
	public ServidorRepository servidorRepository;
	
	public void instantiateTestDatabase() throws ParseException{
		Servidor ser1 = new Servidor(null, "1234", "Teste", "teste@gmail.com");
		Servidor ser2 = new Servidor(null, "4321", "Teste 2", "teste2@gmail.com");
		
		servidorRepository.saveAll(Arrays.asList(ser1,ser2));
	}
}
