package br.com.fundatec.repository;

import br.com.fundatec.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Integer> {

    Optional<Conta> findContaByNumero(Integer numero);
}
