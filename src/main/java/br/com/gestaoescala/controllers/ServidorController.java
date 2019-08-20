package br.com.gestaoescala.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestaoescala.domain.Servidor;
import br.com.gestaoescala.services.ServidorService;

@RestController
@RequestMapping(value="/servidores")
public class ServidorController {

	@Autowired
	private ServidorService servidorService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscaPorId(@PathVariable Integer id){
		Servidor servidor = servidorService.buscar(id);
		
		return ResponseEntity.ok(servidor);
	}

}
