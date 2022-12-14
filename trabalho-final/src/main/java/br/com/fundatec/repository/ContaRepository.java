package br.com.fundatec.repository;

import br.com.fundatec.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Integer> {
}
