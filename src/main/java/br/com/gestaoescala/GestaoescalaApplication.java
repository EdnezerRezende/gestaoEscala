package br.com.gestaoescala;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gestaoescala.domain.Servidor;
import br.com.gestaoescala.repositories.ServidorRepository;

@SpringBootApplication
public class GestaoescalaApplication implements CommandLineRunner {

	@Autowired
	public ServidorRepository servidorRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(GestaoescalaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Servidor ser1 = new Servidor(null, "1234", "Teste", "teste@gmail.com");
		Servidor ser2 = new Servidor(null, "4321", "Teste 2", "teste2@gmail.com");
		
		servidorRepository.saveAll(Arrays.asList(ser1,ser2));
	}
	
}
