package br.com.fundatec.repository;

import br.com.fundatec.model.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <h1> INTERFACE AgenciaRepository</h1>
 *
 * <p> Classe responsavel por fazer a conexao com o banco de dados da Agencia.</p>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @since 1.0
 */
@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {

    Optional<Agencia> findAgenciaByNome(String nome);

    Optional<Agencia> findAgenciaByNumero(Integer numero);
}
