package br.com.fundatec.service;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.AgenciaCreateDTO;
import br.com.fundatec.dto.AgenciaDTO;
import br.com.fundatec.dto.AgenciaUpdateDTO;
import br.com.fundatec.dto.BancoDTO;
import br.com.fundatec.model.Agencia;
import br.com.fundatec.model.Banco;
import br.com.fundatec.repository.AgenciaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h1> Classe AgenciaServiec</h1>
 *
 * <p> Classe responsavel pelo CRUD e por gerir as regras de negocio.</p>
 * <p> Contem vinculo direto com AgenciaRepository para poder persistir as informações necessáras</p>
 * <p>Possui os seguintes metodos</p>
 * <ul>
 *    <li>verificarSeAgenciaExiste - <b>private</b></li>
 *    <li>create - <b>public</b></li>
 *    <li>update - <b>public</b></li>
 *    <li>agenciaFindById - <b>public</b></li>
 *    <li>findById - <b>private</b></li>
 *    <li>list -  <b>public</b></li>
 *    <li>delete - <b>public</b></li>
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @see AgenciaRepository
 * @since 1.0
 */
@RequiredArgsConstructor
@Service
public class AgenciaService {

    private final AgenciaRepository agenciaRepository;

    private final BancoService bancoService;
    private final ObjectMapper objectMapper;


    /**
     * Metodo para verificar se há tentativa de duplicidade no Banco de dados
     *
     * @param agenciaCreateDTO AgenciaCreateDTO
     * @return {@link Void} void
     * @throws RegraDeNegocioException - Caso o agenciaCreateDTO possua informacoes iguais a alguma outra agencia que exista no banco de dados
     *                                 ele lança uma excecao, informando o motivo.
     *                                 <p>Motivos para lancar a excecao: </p>
     *                                   <ul>
     *                                      <li>Agencia com o mesmo nome já existente no banco de dados</li>
     *                                      <li>Agencia com o mesmo numero já existente no banco de dados</li>
     *                                 </ul>
     */
    private void verificarSeAgenciaExiste(AgenciaCreateDTO agenciaCreateDTO) throws RegraDeNegocioException {
        Optional<Agencia> agenciaFoundByNome = agenciaRepository.findAgenciaByNome(agenciaCreateDTO.getNome());
        if (agenciaFoundByNome.isPresent()) {
            throw new RegraDeNegocioException("Agencia com este nome já existe");
        }

        Optional<Agencia> agenciaFoundByNumero = agenciaRepository.findAgenciaByNumero(agenciaCreateDTO.getNumero());
        if (agenciaFoundByNumero.isPresent()) {
            throw new RegraDeNegocioException("Agencia com este numero já existe");
        }
    }

    /**
     * Metodo para criar uma nova entidade de Agencia
     *
     * @param agenciaCreateDTO agenciaCreateDTO
     * @return {@link AgenciaCreateDTO} agenciaCreateDTO
     * @throws RegraDeNegocioException - Caso o agenciaCreateDTO possua informacoes iguais a alguma outra agencia que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     *
     * @see AgenciaCreateDTO
     * @see AgenciaDTO
     */
    public AgenciaDTO create(AgenciaCreateDTO agenciaCreateDTO) throws RegraDeNegocioException {

        verificarSeAgenciaExiste(agenciaCreateDTO);

        BancoDTO bancoDTO = bancoService.bancoFindById(agenciaCreateDTO.getIdBanco());
        Banco banco = objectMapper.convertValue(bancoService.bancoFindById(agenciaCreateDTO.getIdBanco()), Banco.class);
        Agencia agenciaEntity = objectMapper.convertValue(agenciaCreateDTO, Agencia.class);
        agenciaEntity.setBanco(objectMapper.convertValue(bancoService.bancoFindById(agenciaCreateDTO.getIdBanco()), Banco.class));


        Agencia agenciaRetorno = agenciaRepository.save(agenciaEntity);
        AgenciaDTO agenciaDTO = objectMapper.convertValue(agenciaRetorno, AgenciaDTO.class);
        agenciaDTO.setBancoDTO(objectMapper.convertValue(agenciaEntity.getBanco(), BancoDTO.class));

        return agenciaDTO;
    }


    /**
     * Metodo para editar uma nova entidade de Agencia
     *
     * @param agenciaUpdateDTO agenciaUpdateDTO
     * @param idAgencia Integer
     * @return {@link AgenciaDTO} agenciaDTO
     * @throws RegraDeNegocioException - Caso o agenciaCreateDTO possua informacoes iguais a alguma outra agencia que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     *
     * @see AgenciaUpdateDTO
     */
    public AgenciaDTO update(Integer idAgencia, AgenciaUpdateDTO agenciaUpdateDTO) throws RegraDeNegocioException {
        Agencia agenciaRetorno = findById(idAgencia);

        agenciaRetorno.setNome(agenciaUpdateDTO.getNome());
        agenciaRetorno.setNumero(agenciaUpdateDTO.getNumero());
        agenciaRepository.save(agenciaRetorno);

        AgenciaDTO agenciaDTO = new AgenciaDTO();
        agenciaDTO.setNome(agenciaRetorno.getNome());
        agenciaDTO.setIdAgencia(agenciaRetorno.getIdAgencia());
        agenciaDTO.setNumero(agenciaRetorno.getNumero());
        BancoDTO bancoDTO = bancoService.bancoFindById(agenciaRetorno.getBanco().getIdBanco());
        agenciaDTO.setBancoDTO(bancoDTO);

        return agenciaDTO;
    }

    /**
     * Metodo para buscar uma Agencia por ID existente no Banco de dados
     *
     * @param idAgencia Integer
     * @return {@link AgenciaDTO} agenciaDTO
     * @throws RegraDeNegocioException - Caso o agenciaCreateDTO possua informacoes iguais a alguma outra agencia que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     *
     * @see AgenciaDTO
     */
    public AgenciaDTO agenciaFindById(Integer idAgencia) throws RegraDeNegocioException {
        Agencia agencia = findById(idAgencia);
        AgenciaDTO agenciaDTO = new AgenciaDTO();
        agenciaDTO.setIdAgencia(agencia.getIdAgencia());
        agenciaDTO.setNome(agencia.getNome());
        agenciaDTO.setNumero(agencia.getNumero());
        BancoDTO bancoDTO = bancoService.bancoFindById(agencia.getBanco().getIdBanco());
        agenciaDTO.setBancoDTO(bancoDTO);

        return agenciaDTO;
    }


    /**
     * Metodo para buscar uma Agencia por ID existente no Banco de dados
     *
     * @param idAgencia Integer
     * @return {@link Agencia} agenciaRetorno
     * @throws RegraDeNegocioException -  Caso o ID informado nao exista no banco de dados
     *                                     ele lanca uma excecao, informando o motivo.
     *
     * @see Agencia
     */
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

    /**
     * Metodo para buscar uma lista com todas as Agencias existentes no Banco de dados
     *
     * @return {@link AgenciaDTO} agenciaDTO.
     */
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
    }

    /**
     * Metodo para deletar Agencia por ID existente no Banco de dados
     *
     * @param idAgencia Integer
     * @throws RegraDeNegocioException - Caso o ID informado nao exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     */
    public void delete(Integer idAgencia) throws RegraDeNegocioException {
        Agencia agencia = findById(idAgencia);

        agenciaRepository.deleteById(agencia.getIdAgencia());

    }

}
