package br.com.gestaoescala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestaoescala.domain.Licencas;

@Repository
public interface LicencasRepository extends JpaRepository<Licencas, Integer>{

}
