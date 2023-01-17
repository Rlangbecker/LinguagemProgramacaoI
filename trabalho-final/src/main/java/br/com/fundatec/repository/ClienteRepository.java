package br.com.fundatec.repository;

import br.com.fundatec.model.Banco;
import br.com.fundatec.model.Cliente;
import br.com.fundatec.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <h1> INTERFACE ClienteRepository</h1>
 *
 * <p> Classe responsavel por fazer a conexao com o banco de dados dos Clientes.</p>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @since 1.0
 */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findClienteByCpf(String cpf);
}
