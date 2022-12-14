package br.com.fundatec.service;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.AgenciaCreateDTO;
import br.com.fundatec.dto.AgenciaDTO;
import br.com.fundatec.model.Agencia;
import br.com.fundatec.repository.AgenciaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AgenciaService {

    private AgenciaRepository agenciaRepository;
    private ObjectMapper objectMapper;


    public AgenciaDTO create(AgenciaCreateDTO agenciaCreateDTO){

        Agencia agenciaEntity = objectMapper.convertValue(agenciaCreateDTO,Agencia.class);

        AgenciaDTO agenciaDTO = objectMapper.convertValue(agenciaRepository.save(agenciaEntity), AgenciaDTO.class);

        return agenciaDTO;
    }

    public AgenciaDTO update (Integer idAgencia,AgenciaCreateDTO agenciaCreateDTO) throws RegraDeNegocioException {
       Agencia agenciaRetorno=findById(idAgencia);

       agenciaRetorno.setNome(agenciaCreateDTO.getNome());
       agenciaRetorno.setNumero(agenciaCreateDTO.getNumero());

      AgenciaDTO agenciaDTO= objectMapper.convertValue(agenciaRepository.save(agenciaRetorno), AgenciaDTO.class);

      return agenciaDTO;
    }

    public AgenciaDTO agenciaFindById(Integer idAgencia) throws RegraDeNegocioException {
        AgenciaDTO agenciaDTO = objectMapper.convertValue(findById(idAgencia), AgenciaDTO.class);

        return agenciaDTO;
    }


    private Agencia findById(Integer idAgencia) throws RegraDeNegocioException{
       Optional<Agencia> agencia = agenciaRepository.findById(idAgencia);
       if(agencia.isEmpty()){
        throw new RegraDeNegocioException("Agencia não encontrada");
       }
       Agencia agenciaRetorno = objectMapper.convertValue(agencia,Agencia.class);

       return agenciaRetorno;
    }

    public List<AgenciaDTO> list(){
       return agenciaRepository.findAll().stream()
               .map(agencia -> objectMapper.convertValue(agencia, AgenciaDTO.class))
               .collect(Collectors.toList());

    }

    public void delete(Integer idAgencia) throws RegraDeNegocioException {
       Agencia agencia = findById(idAgencia);

       agenciaRepository.deleteById(agencia.getId());

    }

}
