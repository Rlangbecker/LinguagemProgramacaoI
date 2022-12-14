package br.com.fundatec.repository;

import br.com.fundatec.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository <Banco,Integer> {
}
