package br.com.gestaoescala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestaoescala.domain.Escala;

@Repository
public interface EscalaRepository extends JpaRepository<Escala, Integer>{

}
