package br.com.fundatec.repository;

import br.com.fundatec.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BancoRepository extends JpaRepository <Banco,Integer> {

    Optional<Banco> findBancoByNome(String nome);

    Optional<Banco> findBancoByCnpj(String cnpj);

    Optional<Banco> findBancoByCodigo(Integer codigo);

}
