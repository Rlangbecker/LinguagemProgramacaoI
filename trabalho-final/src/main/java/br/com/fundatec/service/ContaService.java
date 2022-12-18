package br.com.fundatec.service;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.*;
import br.com.fundatec.model.Agencia;
import br.com.fundatec.model.Cliente;
import br.com.fundatec.model.Conta;
import br.com.fundatec.model.TipoConta;
import br.com.fundatec.repository.ContaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContaService {
    private final ObjectMapper objectMapper;
    private final ContaRepository contaRepository;
    private final AgenciaService agenciaService;
    private final ClienteService clienteService;

    public ContaDTO create(TipoConta tipoConta, ContaCreateDTO contaCreateDTO) throws RegraDeNegocioException {
        Conta conta = objectMapper.convertValue(contaCreateDTO, Conta.class);
        conta.setTipoConta(tipoConta);
        Cliente cliente = objectMapper.convertValue(clienteService.clienteFindById(contaCreateDTO.getIdCliente()), Cliente.class);
        conta.setCliente(cliente);
        Agencia agencia = objectMapper.convertValue(agenciaService.agenciaFindById(contaCreateDTO.getIdAgencia()), Agencia.class);
        conta.setAgencia(agencia);

        ContaDTO contaDTO = objectMapper.convertValue(contaRepository.save(conta), ContaDTO.class);

        contaDTO.setAgencia(agenciaService.agenciaFindById(contaCreateDTO.getIdAgencia()));
        contaDTO.setCliente(clienteService.clienteFindById(contaCreateDTO.getIdCliente()));
        return contaDTO;
    }

    public ContaDTO update(Integer id, ContaCreateDTO contaCreateDTO, TipoConta tipoConta) throws RegraDeNegocioException {
        Conta conta = findById(id);

        conta.setTipoConta(tipoConta);
        conta.setNumero(contaCreateDTO.getNumero());

        ContaDTO contaDTO = objectMapper.convertValue(contaRepository.save(conta), ContaDTO.class);
        return contaDTO;
    }

    public List<ContaDTO> list() {


        List<ContaDTO> listaDTO = contaRepository.findAll().stream()
                .map(conta -> {
                    ContaDTO contaDTO = new ContaDTO();
                    contaDTO.setIdConta(conta.getIdConta());
                    contaDTO.setNumero(conta.getNumero());
                    contaDTO.setSaldo(conta.getSaldo());
                    contaDTO.setTipoConta(conta.getTipoConta());
                    AgenciaDTO agencia = new AgenciaDTO();
                    ClienteDTO cliente = new ClienteDTO();
                    try {
                        agencia = agenciaService.agenciaFindById(conta.getAgencia().getIdAgencia());
                        contaDTO.setAgencia(agencia);
                        cliente = clienteService.clienteFindById(conta.getCliente().getIdCliente());
                        contaDTO.setCliente(cliente);
                    } catch (RegraDeNegocioException e) {
                        throw new RuntimeException(e);
                    }
                    return contaDTO;
                })
                .collect(Collectors.toList());
        if (listaDTO.isEmpty()) {
            return contaRepository.findAll().stream()
                    .map(conta -> objectMapper.convertValue(conta, ContaDTO.class))
                    .collect(Collectors.toList());
        }

        return listaDTO;
    }

    public List<ContaDTO> listByTipo(TipoConta tipoConta) {
        List<Conta> listAll = contaRepository.findAll();
        if (tipoConta == TipoConta.POUPANCA) {
            return listAll.stream()
                    .map(conta -> {
                        ContaDTO contaDTO = new ContaDTO();
                        contaDTO.setIdConta(conta.getIdConta());
                        contaDTO.setNumero(conta.getNumero());
                        contaDTO.setSaldo(conta.getSaldo());
                        contaDTO.setTipoConta(conta.getTipoConta());
                        AgenciaDTO agencia = new AgenciaDTO();
                        ClienteDTO cliente = new ClienteDTO();
                        try {
                            agencia = agenciaService.agenciaFindById(conta.getAgencia().getIdAgencia());
                            contaDTO.setAgencia(agencia);
                            cliente = clienteService.clienteFindById(conta.getCliente().getIdCliente());
                            contaDTO.setCliente(cliente);
                        } catch (RegraDeNegocioException e) {
                            throw new RuntimeException(e);
                        }
                        return contaDTO;
                    }).filter(contaDTO -> contaDTO.getTipoConta() == TipoConta.POUPANCA)
                    .collect(Collectors.toList());
        } else {
            return listAll.stream()
                    .map(conta -> {
                        ContaDTO contaDTO = new ContaDTO();
                        contaDTO.setIdConta(conta.getIdConta());
                        contaDTO.setNumero(conta.getNumero());
                        contaDTO.setSaldo(conta.getSaldo());
                        contaDTO.setTipoConta(conta.getTipoConta());
                        AgenciaDTO agencia = new AgenciaDTO();
                        ClienteDTO cliente = new ClienteDTO();
                        try {
                            agencia = agenciaService.agenciaFindById(conta.getAgencia().getIdAgencia());
                            contaDTO.setAgencia(agencia);
                            cliente = clienteService.clienteFindById(conta.getCliente().getIdCliente());
                            contaDTO.setCliente(cliente);
                        } catch (RegraDeNegocioException e) {
                            throw new RuntimeException(e);
                        }
                        return contaDTO;
                    }).filter(contaDTO -> contaDTO.getTipoConta() == TipoConta.CORRENTE)
                    .collect(Collectors.toList());
        }


    }

    public ContaDTO contaFindById(Integer id) throws RegraDeNegocioException {
        Conta conta = findById(id);

        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setTipoConta(conta.getTipoConta());
        contaDTO.setNumero(conta.getNumero());
        contaDTO.setSaldo(conta.getSaldo());
        contaDTO.setIdConta(conta.getIdConta());
        AgenciaDTO agenciaDTO = agenciaService.agenciaFindById(conta.getAgencia().getIdAgencia());
        contaDTO.setAgencia(agenciaDTO);
        ClienteDTO clienteDTO = clienteService.clienteFindById(conta.getCliente().getIdCliente());
        contaDTO.setCliente(clienteDTO);
        return contaDTO;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        Conta conta = findById(id);

        contaRepository.delete(conta);
    }

    private Conta findById(Integer idConta) throws RegraDeNegocioException {
        Optional<Conta> contaRetorno = contaRepository.findById(idConta);

        if (contaRetorno.isEmpty()) {
            throw new RegraDeNegocioException("Conta não encontrada!");
        }
        Conta conta = new Conta();
        conta.setIdConta(contaRetorno.get().getIdConta());
        conta.setTipoConta(contaRetorno.get().getTipoConta());
        conta.setSaldo(contaRetorno.get().getSaldo());
        conta.setCliente(contaRetorno.get().getCliente());
        conta.setNumero(contaRetorno.get().getNumero());
        conta.setAgencia(contaRetorno.get().getAgencia());
        return conta;
    }
}
