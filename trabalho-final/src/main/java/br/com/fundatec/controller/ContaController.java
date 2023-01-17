package br.com.fundatec.controller;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.AgenciaCreateDTO;
import br.com.fundatec.dto.AgenciaDTO;
import br.com.fundatec.dto.ContaCreateDTO;
import br.com.fundatec.dto.ContaDTO;
import br.com.fundatec.model.Conta;
import br.com.fundatec.model.TipoConta;
import br.com.fundatec.repository.AgenciaRepository;
import br.com.fundatec.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h1> Classe ContaController</h1>
 *
 * <p> Classe responsavel por intermediar as requisicoes enviadas pelo View com as respostas fornecidas pelo Model,
 * processando os dados que o usuario informou e repassando para outras camadas.</p>
 * <p>Possui as seguintes propriedades: </p>
 * <ul>
 *    <li>contaService - ContaService </li>
 *    <li>create - <b>public</b></li>
 *    <li>list - <b>public</b></li>
 *    <li>findById - <b>public</b></li>
 *    <li>listByTipoConta - <b>public</b></li>
 *    <li>update - <b>public</b></li>
 *    <li>delete - <b>public</b></li>
 *
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @see ContaService
 * @since 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/conta")
public class ContaController {
    private final ContaService contaService;

    /**
     * Metodo para criar uma nova entidade de Conta
     * Metodo usa verbo HTTP Post
     *
     * @param contaCreateDTO contaCreateDTO
     * @param tipoConta      tipoConta
     * @return {@link ContaCreateDTO} contaCreateDTO
     * @throws RegraDeNegocioException - Caso o agenciaCreateDTO possua informacoes iguais a alguma outra conta que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     * @see ContaCreateDTO
     * @see ContaDTO
     * @see TipoConta
     */
    @Operation(summary = "Criar nova Conta", description = "Cria uma nova conta")
    @PostMapping
    public ResponseEntity<ContaDTO> create(@RequestParam TipoConta tipoConta, @RequestBody ContaCreateDTO contaCreateDTO) throws RegraDeNegocioException {
        ContaDTO contaDTO = contaService.create(tipoConta, contaCreateDTO);

        return new ResponseEntity<>(contaDTO, HttpStatus.OK);
    }

    /**
     * Metodo para buscar uma lista com todas as Contas existentes no Banco de dados
     *
     * @return {@link ContaDTO} contaDTO.
     */
    @Operation(summary = "Listar todas contas", description = "Retorna uma lista com todas contas")
    @GetMapping
    public ResponseEntity<List<ContaDTO>> list() {
        return new ResponseEntity<>(contaService.list(), HttpStatus.OK);
    }

    /**
     * Metodo para buscar uma Conta por ID existente no Banco de dados
     *
     * @param idConta Integer
     * @return {@link ContaDTO} contaDTO
     * @throws RegraDeNegocioException - Caso o ID informado nao exista no banco de dados
     *                                 ele lança uma excecao, informando o motivo.
     * @see ContaDTO
     */
    @Operation(summary = "Buscar conta por ID", description = "Retorna uma conta pelo ID inserido")
    @GetMapping("/find-by-id")
    public ResponseEntity<ContaDTO> findById(@RequestParam Integer idConta) throws RegraDeNegocioException {
        ContaDTO contaDTO = contaService.contaFindById(idConta);
        return new ResponseEntity<>(contaDTO, HttpStatus.OK);
    }

    /**
     * Metodo para buscar uma lista com os tipos de Conta existentes no Banco de dados
     *
     * @param tipoConta TipoConta
     * @return {@link ContaDTO} contaDTO.
     * @throws RegraDeNegocioException -  Caso o ID informado nao exista no banco de dados
     *                                      ele lança uma exceção, informando o motivo.
     */
    @Operation(summary = "Listar todas contas pelo TIPO", description = "Retorna uma lista com todas contas pelo TIPO")
    @GetMapping("/list-by-tipo-conta")
    public ResponseEntity<List<ContaDTO>> listByTipoConta(@RequestParam TipoConta tipoConta) throws RegraDeNegocioException {
        List<ContaDTO> listByTipo = contaService.listByTipo(tipoConta);
        return new ResponseEntity<>(listByTipo, HttpStatus.OK);
    }

    /**
     * Metodo para editar uma nova entidade de Conta
     *
     * @param contaCreateDTO contaCreateDTO
     * @param idConta        Integer
     * @param tipoConta      TipoConta
     * @return {@link ContaDTO} contaDTO
     * @throws RegraDeNegocioException - Caso o agenciaCreateDTO possua informacoes iguais a alguma outra conta que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     * @see ContaCreateDTO
     */
    @Operation(summary = "Atualizar conta", description = "Atualiza dados da conta pelo ID informado")
    @PutMapping("/update")
    public ResponseEntity<ContaDTO> update(@RequestParam Integer idConta, @RequestParam TipoConta tipoConta, @RequestBody ContaCreateDTO contaCreateDTO) throws RegraDeNegocioException {
        ContaDTO contaDTO = contaService.update(idConta, contaCreateDTO, tipoConta);
        return new ResponseEntity<>(contaDTO, HttpStatus.OK);
    }

    /**
     * Metodo para deletar Conta por ID existente no Banco de dados
     *
     * @param idConta Integer
     * @return {@link Void} void
     * @throws RegraDeNegocioException - Caso o ID informado nao exista no banco de dados
     *                                 ele lança uma excecao, informando o motivo.
     */
    @Operation(summary = "Deletar conta", description = "Deleta conta pelo ID informado")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Integer idConta) throws RegraDeNegocioException {
        contaService.delete(idConta);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
