package br.com.gestaoescala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestaoescala.domain.Servidor;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Integer>{

}
