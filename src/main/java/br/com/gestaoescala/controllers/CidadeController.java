package br.com.gestaoescala.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestaoescala.domain.Cidade;
import br.com.gestaoescala.dto.CidadeDTO;
import br.com.gestaoescala.dto.EstadoDTO;
import br.com.gestaoescala.services.CidadeService;

@RestController
@RequestMapping(value="/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	
	@RequestMapping(value="/estados/{estado_id}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findAll(@PathVariable Integer id) {
		List<Cidade> list = cidadeService.buscarTodos(id);
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
}
