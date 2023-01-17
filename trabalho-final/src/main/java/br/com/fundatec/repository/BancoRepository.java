package br.com.fundatec.repository;

import br.com.fundatec.model.Banco;
import br.com.fundatec.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <h1> INTERFACE BancoRepository</h1>
 *
 * <p> Classe responsavel por fazer a conexao com o banco de dados do Banco.</p>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @since 1.0
 */

@Repository
public interface BancoRepository extends JpaRepository<Banco, Integer> {

    Optional<Banco> findBancoByNome(String nome);

    Optional<Banco> findBancoByCnpj(String cnpj);

    Optional<Banco> findBancoByCodigo(Integer codigo);

}
