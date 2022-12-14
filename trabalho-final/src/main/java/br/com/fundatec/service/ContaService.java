package br.com.fundatec.service;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.ContaCreateDTO;
import br.com.fundatec.dto.ContaDTO;
import br.com.fundatec.model.Cliente;
import br.com.fundatec.model.Conta;
import br.com.fundatec.repository.ContaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContaService {
    private final ObjectMapper objectMapper;
    private final ContaRepository contaRepository;

    public ContaDTO create(ContaCreateDTO contaCreateDTO) {
        Conta conta = objectMapper.convertValue(contaCreateDTO, Conta.class);

        ContaDTO contaDTO = objectMapper.convertValue(contaRepository.save(conta), ContaDTO.class);
        return contaDTO;
    }

    public ContaDTO update(Integer id, ContaCreateDTO contaCreateDTO) throws RegraDeNegocioException {
        Conta conta = findById(id);

        conta.setTipoConta(contaCreateDTO.getTipoConta());
        conta.setNumero(contaCreateDTO.getNumero());

        ContaDTO contaDTO = objectMapper.convertValue(contaRepository.save(conta), ContaDTO.class);
        return contaDTO;
    }

    public List<ContaDTO> list() {
        return contaRepository.findAll().stream()
                .map(conta -> objectMapper.convertValue(conta, ContaDTO.class))
                .collect(Collectors.toList());
    }

    public ContaDTO contaFindById(Integer id) throws RegraDeNegocioException {
        Conta conta = findById(id);

        ContaDTO contaDTO = objectMapper.convertValue(conta, ContaDTO.class);
        return contaDTO;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        Conta conta = findById(id);

        contaRepository.delete(conta);
    }

    private Conta findById(Integer idConta) throws RegraDeNegocioException {
        Optional contaRetorno = contaRepository.findById(idConta);

        if (contaRetorno.isEmpty()) {
            throw new RegraDeNegocioException("Conta não encontrada!");
        }
        Conta conta = objectMapper.convertValue(contaRetorno, Conta.class);
        return conta;
    }
}
