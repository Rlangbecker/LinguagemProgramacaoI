package br.com.fundatec.repository;

import br.com.fundatec.dto.AgenciaDTO;
import br.com.fundatec.model.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia,Integer> {
//    @Query("select new br.com.fundatec.dto.AgenciaDTO(a.nome, a.) from AgenciaDTO a ")
//    List<AgenciaDTO> query();

}
