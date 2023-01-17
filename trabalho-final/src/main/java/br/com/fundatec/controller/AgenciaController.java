package br.com.fundatec.controller;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.AgenciaCreateDTO;
import br.com.fundatec.dto.AgenciaDTO;
import br.com.fundatec.dto.AgenciaUpdateDTO;
import br.com.fundatec.model.Agencia;
import br.com.fundatec.service.AgenciaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h1> Classe ContaDTO</h1>
 *
 * <p>DTO (data transfer object) reponsavel pela transferencia de dados</p>
 * <p>Possui as seguintes propriedades: </p>
 * <ul>
 *    <li>idConta - Integer </li>
 *    <li>cliente - ClienteDTO </li>
 *    <li>agencia - AgenciaDTO </li>
 *    <li>numero - Integer </li>
 *    <li>saldo - double</li>
 *    <li>tipoConta - TipoConta</li>
 *
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @see AgenciaService
 * @since 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/agencia")
public class AgenciaController {
    private final AgenciaService agenciaService;

    /**
     * Metodo para criar uma nova entidade de Agencia
     * Metodo usa verbo HTTP Post
     *
     * @param agenciaCreateDTO agenciaCreateDTO
     * @return {@link AgenciaCreateDTO} agenciaCreateDTO
     * @throws RegraDeNegocioException - Caso o agenciaCreateDTO possua informacoes iguais a alguma outra agencia que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     * @see AgenciaCreateDTO
     * @see AgenciaDTO
     */
    @Operation(summary = "Criar agencia", description = "Criar agencia")
    @PostMapping
    public ResponseEntity<AgenciaDTO> create(@RequestBody AgenciaCreateDTO agenciaCreateDTO) throws RegraDeNegocioException {
        AgenciaDTO agenciaDTO = agenciaService.create(agenciaCreateDTO);

        return new ResponseEntity<>(agenciaDTO, HttpStatus.OK);
    }

    /**
     * Metodo para buscar uma lista com todas as Agencias existentes no Banco de dados
     *
     * @return {@link AgenciaDTO} agenciaDTO.
     */
    @Operation(summary = "Listar agencias", description = "Retorna uma lista de todas as agencias do banco")
    @GetMapping
    public ResponseEntity<List<AgenciaDTO>> list() {
        List<AgenciaDTO> lista = agenciaService.list();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    /**
     * Metodo para buscar uma Agencia por ID existente no Banco de dados
     *
     * @param idAgencia Integer
     * @return {@link Agencia} agenciaRetorno
     * @throws RegraDeNegocioException -  Caso o ID informado nao exista no banco de dados
     *                                 ele lança uma exceção, informando o motivo.
     * @see Agencia
     */
    @Operation(summary = "Buscar agencia", description = "Retorna agencia pelo ID inserido")
    @GetMapping("/find-by-id")
    public ResponseEntity<AgenciaDTO> findById(@RequestParam Integer idAgencia) throws RegraDeNegocioException {
        AgenciaDTO agenciaDTO = agenciaService.agenciaFindById(idAgencia);

        return new ResponseEntity<>(agenciaDTO, HttpStatus.OK);
    }

    /**
     * Metodo para editar uma nova entidade de Agencia
     *
     * @param agenciaUpdateDTO agenciaUpdateDTO
     * @param idAgencia        Integer
     * @return {@link AgenciaDTO} agenciaDTO
     * @throws RegraDeNegocioException - Caso o agenciaCreateDTO possua informacoes iguais a alguma outra agência que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     * @see AgenciaUpdateDTO
     */
    @Operation(summary = "Editar agencia", description = "Edita uma agencia pelo ID inserido")
    @PutMapping("/update")
    public ResponseEntity<AgenciaDTO> update(@RequestParam Integer idAgencia, @RequestBody AgenciaUpdateDTO agenciaUpdateDTO) throws RegraDeNegocioException {
        AgenciaDTO agenciaDTO = agenciaService.update(idAgencia, agenciaUpdateDTO);

        return new ResponseEntity<>(agenciaDTO, HttpStatus.OK);
    }

    /**
     * Metodo para deletar Agencia por ID existente no Banco de dados
     *
     * @param idAgencia Integer
     * @return {@link Void} void
     * @throws RegraDeNegocioException - Caso o ID informado nao exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     */
    @Operation(summary = "Deletar agencia", description = "Deleta uma agencia pelo ID inserido")
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Integer idAgencia) throws RegraDeNegocioException {
        agenciaService.delete(idAgencia);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
