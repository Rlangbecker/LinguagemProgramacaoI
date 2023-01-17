package br.com.fundatec.service;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.AgenciaDTO;
import br.com.fundatec.dto.ClienteCreateDTO;
import br.com.fundatec.dto.ClienteDTO;
import br.com.fundatec.model.Agencia;
import br.com.fundatec.model.Cliente;
import br.com.fundatec.repository.BancoRepository;
import br.com.fundatec.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h1> Classe ClienteService</h1>
 *
 * <p> Classe responsavel pelo CRUD e por gerir as regras de negocio.</p>
 * <p> Contem vinculo direto com ClienteRepository para poder persistir as informacoes necessarias</p>
 * <p>Possui os seguintes metodos</p>
 * <ul>
 *    <li>create - <b>public</b></li>
 *    <li>update - <b>public</b></li>
 *    <li>list - <b>public</b></li>
 *    <li>clienteFindById - <b>public</b></li>
 *    <li>delete - <b>public</b></li>
 *    <li>findById - <b>private</b></li>
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @see ClienteRepository
 * @since 1.0
 */
@RequiredArgsConstructor
@Service
public class ClienteService {
    private final ObjectMapper objectMapper;
    private final ClienteRepository clienteRepository;

    /**
     * Metodo para criar uma nova entidade de Cliente
     *
     * @param clienteCreateDTO clienteCreateDTO
     * @return {@link ClienteCreateDTO} clienteCreateDTO
     * @throws RegraDeNegocioException - Caso o clienteCreateDTO possua informacoes iguais a algum outro cliente que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     */
    public ClienteDTO create(ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {

        Optional<Cliente> clienteFoundByCpf = clienteRepository.findClienteByCpf(clienteCreateDTO.getCpf());
        if (clienteFoundByCpf.isPresent()) {
            throw new RegraDeNegocioException("CPF já cadastrado!");
        }

        Cliente cliente = objectMapper.convertValue(clienteCreateDTO, Cliente.class);

        ClienteDTO clienteDTO = objectMapper.convertValue(clienteRepository.save(cliente), ClienteDTO.class);

        return clienteDTO;
    }

    /**
     * Metodo para editar Cliente ja existente no Banco de dados
     *
     * @param clienteCreateDTO clienteCreateDTO
     * @param id               Integer
     * @return {@link ClienteDTO} clienteDTO
     * @throws RegraDeNegocioException - Caso o clienteCreateDTO possua informacoes iguais a algum outro cliente que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     */
    public ClienteDTO update(Integer id, ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        Cliente cliente = findById(id);

        cliente.setCpf(clienteCreateDTO.getCpf());
        cliente.setNome(clienteCreateDTO.getNome());

        ClienteDTO clienteDTO = objectMapper.convertValue(clienteRepository.save(cliente), ClienteDTO.class);
        return clienteDTO;
    }

    /**
     * Metodo para buscar uma lista com todos os Clientes existentes no Banco de dados
     *
     * @return {@link ClienteDTO} clienteDTO.
     */
    public List<ClienteDTO> list() {
        return clienteRepository.findAll().stream()
                .map(cliente -> objectMapper.convertValue(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Metodo para buscar Cliente por ID existente no Banco de dados
     *
     * @param id Integer
     * @return {@link ClienteDTO} clienteDTO
     * @throws RegraDeNegocioException - Caso o ID informado nao exista no banco de dados
     *                                 ele lança uma exceção, informando o motivo.
     */
    public ClienteDTO clienteFindById(Integer id) throws RegraDeNegocioException {
        Cliente cliente = findById(id);

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(cliente.getIdCliente());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setNome(cliente.getNome());

        return clienteDTO;
    }

    /**
     * Metodo para deletar Cliente por ID existente no Banco de dados
     *
     * @param id Integer
     * @throws RegraDeNegocioException - Caso o ID informado nao exista no banco de dados
     *                                 ele lança uma exceção, informando o motivo.
     */
    public void delete(Integer id) throws RegraDeNegocioException {
        Cliente cliente = findById(id);

        clienteRepository.deleteById(cliente.getIdCliente());
    }

    /**
     * Metodo para buscar um Cliente por ID existente no Banco de dados
     *
     * @param idCliente Integer
     * @return {@link Cliente} clienteRetorno
     * @throws RegraDeNegocioException -  Caso o ID informado nao exista no banco de dados
     *                                 ele lança uma exceção, informando o motivo.
     * @see Cliente
     */
    private Cliente findById(Integer idCliente) throws RegraDeNegocioException {
        Optional<Cliente> clienteRetorno = clienteRepository.findById(idCliente);
        if (clienteRetorno.isEmpty()) {
            throw new RegraDeNegocioException("Cliente não encontrado!");
        }

        Cliente cliente = new Cliente();
        cliente.setIdCliente(clienteRetorno.get().getIdCliente());
        cliente.setNome(clienteRetorno.get().getNome());
        cliente.setCpf(clienteRetorno.get().getCpf());

        return cliente;
    }
}
