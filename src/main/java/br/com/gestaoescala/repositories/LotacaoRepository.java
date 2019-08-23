package br.com.gestaoescala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestaoescala.domain.Lotacao;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Integer>{

}