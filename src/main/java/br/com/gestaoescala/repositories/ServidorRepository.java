package br.com.gestaoescala.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestaoescala.domain.Servidor;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Integer>{

	@Transactional(readOnly=true)
	Servidor findByEmail(String email);
}
