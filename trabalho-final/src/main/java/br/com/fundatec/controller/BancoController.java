package br.com.fundatec.controller;


import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.BancoCreateDTO;
import br.com.fundatec.dto.BancoDTO;
import br.com.fundatec.model.Banco;
import br.com.fundatec.service.BancoService;
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
 * @see BancoService
 * @since 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/banco")
public class BancoController {
    private final BancoService bancoService;

    /**
     * Metodo para criar uma nova entidade de Banco
     * Metodo usa verbo HTTP Post
     *
     * @param bancoCreateDTO BancoCreateDTO
     * @return {@link BancoCreateDTO} bancoCreateDTO
     * @throws RegraDeNegocioException - Caso o bancoCreateDTO possua informacoes iguais a algum outro banco que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     * @see BancoCreateDTO
     * @see BancoDTO
     */
    @Operation(summary = "Criar um banco", description = "Criar um banco")
    @PostMapping
    public ResponseEntity<BancoDTO> create(@RequestBody BancoCreateDTO bancoCreateDTO) throws RegraDeNegocioException {

        BancoDTO bancoDTO = bancoService.create(bancoCreateDTO);

        return new ResponseEntity<>(bancoDTO, HttpStatus.OK);
    }

    /**
     * Metodo para buscar uma lista com todos os bancos existentes no Banco de dados
     *
     * @return {@link BancoDTO} bancoDTO.
     */
    @Operation(summary = "Listar todos os bancos", description = "Retorna uma lista com todos os bancos do banco de dadoss")
    @GetMapping
    public ResponseEntity<List<BancoDTO>> list() {

        List<BancoDTO> lista = bancoService.list();

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    /**
     * Metodo para buscar banco por ID existente no Banco de dados
     *
     * @param idBanco Integer
     * @return {@link BancoDTO} bancoDTO
     * @throws RegraDeNegocioException - Caso o ID informado nao exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     * @see Banco
     */
    @Operation(summary = "Procurar banco por ID", description = "Retorna o Banco pelo ID inserido")
    @GetMapping("/find-by-id")
    public ResponseEntity<BancoDTO> findById(@RequestParam Integer idBanco) throws RegraDeNegocioException {

        BancoDTO bancoDTO = bancoService.bancoFindById(idBanco);

        return new ResponseEntity<>(bancoDTO, HttpStatus.OK);
    }

    /**
     * Metodo para editar banco ja existente no Banco de dados
     *
     * @param bancoCreateDTO bancoCreateDTO
     * @param idBanco        Integer
     * @return {@link BancoDTO} bancoDTO
     * @throws RegraDeNegocioException - Caso o bancoCreateDTO possua informacoes iguais a algum outro banco que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     * @see BancoCreateDTO
     */
    @Operation(summary = "Editar banco", description = "Edita banco pelo ID inserido")
    @PutMapping("/update")
    public ResponseEntity<BancoDTO> update(@RequestParam Integer idBanco, @RequestBody BancoCreateDTO bancoCreateDTO) throws RegraDeNegocioException {

        BancoDTO bancoDTO = bancoService.update(idBanco, bancoCreateDTO);

        return new ResponseEntity<>(bancoDTO, HttpStatus.OK);
    }

    /**
     * Metodo para deletar banco por ID existente no Banco de dados
     *
     * @param idBanco Integer
     * @return {@link Void} void
     * @throws RegraDeNegocioException - Caso o ID informado nao exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     */
    @Operation(summary = "Deletar banco", description = "Deleta banco pelo ID inseridos")
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Integer idBanco) throws RegraDeNegocioException {
        bancoService.delete(idBanco);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
