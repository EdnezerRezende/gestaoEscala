package br.com.gestaoescala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestaoescala.domain.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

}
