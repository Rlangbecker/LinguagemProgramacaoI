package br.com.fundatec.service;


import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.AgenciaDTO;
import br.com.fundatec.dto.BancoCreateDTO;
import br.com.fundatec.dto.BancoDTO;
import br.com.fundatec.model.Agencia;
import br.com.fundatec.model.Banco;
import br.com.fundatec.repository.BancoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BancoService {

    private final BancoRepository bancoRepository;
    private final ObjectMapper objectMapper;


    public BancoDTO create(BancoCreateDTO bancoCreateDTO) {
        Banco bancoRetorno = objectMapper.convertValue(bancoCreateDTO, Banco.class);

        BancoDTO bancoDTO = objectMapper.convertValue(bancoRepository.save(bancoRetorno), BancoDTO.class);

        return bancoDTO;
    }

    public BancoDTO update(Integer idBanco, BancoCreateDTO bancoCreateDTO) throws RegraDeNegocioException {
        Banco bancoRetorno = findById(idBanco);

        bancoRetorno.setNome(bancoCreateDTO.getNome());
        bancoRetorno.setCnpj(bancoCreateDTO.getCnpj());
        bancoRetorno.setCodigo(bancoCreateDTO.getCodigo());

        bancoRepository.save(bancoRetorno);

        BancoDTO bancoDTO = objectMapper.convertValue(bancoRetorno, BancoDTO.class);

        return bancoDTO;
    }

    public BancoDTO bancoFindById(Integer idBanco) throws RegraDeNegocioException {
        Banco banco = findById(idBanco);

        BancoDTO bancoDTO = objectMapper.convertValue(banco, BancoDTO.class);
        return bancoDTO;
    }


    private Banco findById(Integer idBanco) throws RegraDeNegocioException {
        Optional bancoRetorno = bancoRepository.findById(idBanco);
        if (bancoRetorno.isEmpty()) {
            throw new RegraDeNegocioException("Banco não encontrado!");
        }
        Banco banco = objectMapper.convertValue(bancoRetorno, Banco.class);

        return banco;
    }

    public List<BancoDTO> list() {
        return bancoRepository.findAll().stream()
                .map(banco -> objectMapper.convertValue(banco, BancoDTO.class))
                .collect(Collectors.toList());

    }

    public void delete(Integer idBanco) throws RegraDeNegocioException {
        Banco banco = findById(idBanco);

        bancoRepository.deleteById(banco.getId());
    }

}
