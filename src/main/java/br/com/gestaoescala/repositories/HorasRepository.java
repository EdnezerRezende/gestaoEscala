package br.com.gestaoescala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestaoescala.domain.Horas;

@Repository
public interface HorasRepository extends JpaRepository<Horas, Integer>{

}
