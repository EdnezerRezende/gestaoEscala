package br.com.gestaoescala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestaoescala.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}