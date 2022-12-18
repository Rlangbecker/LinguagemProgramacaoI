package br.com.fundatec.service;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.AgenciaCreateDTO;
import br.com.fundatec.dto.AgenciaDTO;
import br.com.fundatec.dto.BancoDTO;
import br.com.fundatec.model.Agencia;
import br.com.fundatec.model.Banco;
import br.com.fundatec.repository.AgenciaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AgenciaService {

    private final AgenciaRepository agenciaRepository;

    private final BancoService bancoService;
    private final ObjectMapper objectMapper;


    public AgenciaDTO create(AgenciaCreateDTO agenciaCreateDTO) throws RegraDeNegocioException {

        BancoDTO bancoDTO = bancoService.bancoFindById(agenciaCreateDTO.getIdBanco());
        Banco banco = objectMapper.convertValue(bancoService.bancoFindById(agenciaCreateDTO.getIdBanco()), Banco.class);
        Agencia agenciaEntity = objectMapper.convertValue(agenciaCreateDTO, Agencia.class);
        agenciaEntity.setBanco(objectMapper.convertValue(bancoService.bancoFindById(agenciaCreateDTO.getIdBanco()), Banco.class));

        Agencia agenciaRetorno = agenciaRepository.save(agenciaEntity);
        AgenciaDTO agenciaDTO = objectMapper.convertValue(agenciaRetorno, AgenciaDTO.class);
        agenciaDTO.setBancoDTO(objectMapper.convertValue(agenciaEntity.getBanco(), BancoDTO.class));

        return agenciaDTO;
    }

    public AgenciaDTO update(Integer idAgencia, AgenciaCreateDTO agenciaCreateDTO) throws RegraDeNegocioException {
        Agencia agenciaRetorno = findById(idAgencia);

        agenciaRetorno.setNome(agenciaCreateDTO.getNome());
        agenciaRetorno.setNumero(agenciaCreateDTO.getNumero());

        AgenciaDTO agenciaDTO = objectMapper.convertValue(agenciaRepository.save(agenciaRetorno), AgenciaDTO.class);

        return agenciaDTO;
    }

    public AgenciaDTO agenciaFindById(Integer idAgencia) throws RegraDeNegocioException {
        Agencia agencia = findById(idAgencia);
        AgenciaDTO agenciaDTO = objectMapper.convertValue(agencia, AgenciaDTO.class);
        BancoDTO bancoDTO = bancoService.bancoFindById(agencia.getBanco().getIdBanco());
        agenciaDTO.setBancoDTO(bancoDTO);

        return agenciaDTO;
    }


    private Agencia findById(Integer idAgencia) throws RegraDeNegocioException {
        Optional<Agencia> agencia = agenciaRepository.findById(idAgencia);
        if (agencia.isEmpty()) {
            throw new RegraDeNegocioException("Agencia não encontrada");
        }

        Agencia agenciaRetorno = new Agencia();
        agenciaRetorno.setIdAgencia(agencia.get().getIdAgencia());
        agenciaRetorno.setNome(agencia.get().getNome());
        agenciaRetorno.setNumero(agencia.get().getNumero());
        Banco banco = objectMapper.convertValue(bancoService.bancoFindById(agencia.get().getBanco().getIdBanco()), Banco.class);
        agenciaRetorno.setBanco(banco);

        return agenciaRetorno;
    }

    public List<AgenciaDTO> list() {
        List<Agencia> listaAgenciaEntity = agenciaRepository.findAll();

        List<AgenciaDTO> listaDTO = listaAgenciaEntity.stream()
                .map(agencia -> {
                    AgenciaDTO agenciaDTO = new AgenciaDTO();
                    agenciaDTO.setIdAgencia(agencia.getIdAgencia());
                    agenciaDTO.setNumero(agencia.getNumero());
                    agenciaDTO.setNome(agencia.getNome());

                    BancoDTO bancoDTO = null;
                    try {
                        bancoDTO = bancoService.bancoFindById(agencia.getBanco().getIdBanco());
                    } catch (RegraDeNegocioException e) {
                        throw new RuntimeException(e);
                    }
                    agenciaDTO.setBancoDTO(bancoDTO);
                    return agenciaDTO;
                })
                .collect(Collectors.toList());


        return listaDTO;
//
//        return agenciaRepository.findAll().stream()
//                .map(agencia -> objectMapper.convertValue(agencia, AgenciaDTO.class))
//                .collect(Collectors.toList());
    }

    public void delete(Integer idAgencia) throws RegraDeNegocioException {
        Agencia agencia = findById(idAgencia);

        agenciaRepository.deleteById(agencia.getIdAgencia());

    }

}
