package br.com.fundatec.repository;

import br.com.fundatec.model.Banco;
import br.com.fundatec.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <h1> INTERFACE ContaRepository</h1>
 *
 * <p> Classe responsavel por fazer a conexao com o banco de dados da Conta.</p>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @since 1.0
 */

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    Optional<Conta> findContaByNumero(Integer numero);
}
