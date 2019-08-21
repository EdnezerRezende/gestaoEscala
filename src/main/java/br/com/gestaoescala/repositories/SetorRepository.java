package br.com.gestaoescala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestaoescala.domain.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer>{

}
