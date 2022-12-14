package br.com.fundatec.repository;

import br.com.fundatec.dto.AgenciaDTO;
import br.com.fundatec.model.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia,Integer> {

    Optional<Agencia> findAgenciaByNome(String nome);
    Optional<Agencia> findAgenciaByNumero(Integer numero);
}
