package br.com.gestaoescala.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gestaoescala.domain.Cidade;
import br.com.gestaoescala.domain.Endereco;
import br.com.gestaoescala.domain.Estado;
import br.com.gestaoescala.domain.Servidor;
import br.com.gestaoescala.repositories.CidadeRepository;
import br.com.gestaoescala.repositories.EstadoRepository;
import br.com.gestaoescala.repositories.ServidorRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	public ServidorRepository servidorRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public void instantiateTestDatabase() throws ParseException{
		Servidor ser1 = new Servidor(null, "1234", "Teste", "teste@gmail.com", pe.encode("1234"));
		Servidor ser2 = new Servidor(null, "4321", "Teste 2", "teste2@gmail.com",pe.encode("1234"));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		ser1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		ser2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", ser1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", ser1, c2);
		Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "281777012", ser2, c2);
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		ser1.getEnderecos().addAll(Arrays.asList(e1, e2));
		ser2.getEnderecos().addAll(Arrays.asList(e3));
		
		servidorRepository.saveAll(Arrays.asList(ser1,ser2));
	}
}
