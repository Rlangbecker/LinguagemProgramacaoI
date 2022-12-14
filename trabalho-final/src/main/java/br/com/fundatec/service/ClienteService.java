package br.com.fundatec.service;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.ClienteCreateDTO;
import br.com.fundatec.dto.ClienteDTO;
import br.com.fundatec.model.Cliente;
import br.com.fundatec.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ObjectMapper objectMapper;
    private final ClienteRepository clienteRepository;

    public ClienteDTO create(ClienteCreateDTO clienteCreateDTO) {
        Cliente cliente = objectMapper.convertValue(clienteCreateDTO, Cliente.class);

        ClienteDTO clienteDTO = objectMapper.convertValue(clienteRepository.save(cliente), ClienteDTO.class);
        return clienteDTO;
    }

    public ClienteDTO update(Integer id, ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        Cliente cliente = findById(id);

        cliente.setCpf(clienteCreateDTO.getCpf());
        cliente.setNome(clienteCreateDTO.getNome());

        ClienteDTO clienteDTO = objectMapper.convertValue(clienteRepository.save(cliente), ClienteDTO.class);
        return clienteDTO;
    }

    public List<ClienteDTO> list() {
        return clienteRepository.findAll().stream()
                .map(cliente -> objectMapper.convertValue(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    public ClienteDTO clienteFindById (Integer id) throws RegraDeNegocioException {
        Cliente cliente = findById(id);

        ClienteDTO clienteDTO = objectMapper.convertValue(cliente, ClienteDTO.class);
        return clienteDTO;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        Cliente cliente = findById(id);

        clienteRepository.deleteById(cliente.getId());
    }

    private Cliente findById(Integer idCliente) throws RegraDeNegocioException {
        Optional clienteRetorno = clienteRepository.findById(idCliente);
        if (clienteRetorno.isEmpty()) {
            throw new RegraDeNegocioException("Cliente não encontrado!");
        }

        Cliente cliente = objectMapper.convertValue(clienteRetorno, Cliente.class);

        return cliente;
    }
}
