package br.com.gestaoescala.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestaoescala.domain.Estado;
import br.com.gestaoescala.dto.EstadoDTO;
import br.com.gestaoescala.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = estadoService.buscarTodos();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
}
