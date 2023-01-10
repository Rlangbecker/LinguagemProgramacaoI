package br.com.fundatec.service;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.AgenciaDTO;
import br.com.fundatec.dto.ClienteCreateDTO;
import br.com.fundatec.dto.ClienteDTO;
import br.com.fundatec.model.Cliente;
import br.com.fundatec.model.Conta;
import br.com.fundatec.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ObjectMapper objectMapper;
    private final ClienteRepository clienteRepository;

    public ClienteDTO create(ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {

        Optional<Cliente> clienteFoundByCpf = clienteRepository.findClienteByCpf(clienteCreateDTO.getCpf());
        if(clienteFoundByCpf.isPresent()){
            throw new RegraDeNegocioException("CPF já cadastrado!");
        }

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

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(cliente.getIdCliente());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setNome(cliente.getNome());

        return clienteDTO;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        Cliente cliente = findById(id);

        clienteRepository.deleteById(cliente.getIdCliente());
    }

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
