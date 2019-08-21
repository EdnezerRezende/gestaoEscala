package br.com.gestaoescala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestaoescala.domain.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer>{

}
