package br.com.gestaoescala.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gestaoescala.domain.Servidor;
import br.com.gestaoescala.dto.ServidorDTO;
import br.com.gestaoescala.dto.ServidorNewDTO;
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

	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<Servidor> find(@RequestParam(value="value") String email) {
		Servidor obj = servidorService.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	

	@PreAuthorize("hasAnyRole('ADMIN','CHEFE_GESTOR')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ServidorNewDTO objDto) {
		Servidor obj = servidorService.fromDTO(objDto);
		obj = servidorService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','CHEFE_GESTOR')")	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ServidorDTO objDto, @PathVariable Integer id) {
		Servidor obj = servidorService.fromDTO(objDto);
		obj.setId(id);
		obj = servidorService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		servidorService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ServidorDTO>> findAll() {
		List<Servidor> list = servidorService.findAll();
		List<ServidorDTO> listDto = list.stream().map(obj -> new ServidorDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ServidorDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Servidor> list = servidorService.findPage(page, linesPerPage, orderBy, direction);
		Page<ServidorDTO> listDto = list.map(obj -> new ServidorDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}	
}
