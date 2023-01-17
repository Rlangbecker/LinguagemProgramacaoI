package br.com.fundatec.controller;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.ClienteCreateDTO;
import br.com.fundatec.dto.ClienteDTO;
import br.com.fundatec.model.Cliente;
import br.com.fundatec.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h1> Classe ClienteController</h1>
 *
 * <p> Classe responsavel pelo CRUD e por gerir as regras de negocio.</p>
 * <p> Contem vinculo direto com AgenciaRepository para poder persistir as informacoes necessarias</p>
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
 * @see ClienteService
 * @since 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    /**
     * Metodo para criar uma nova entidade de Cliente
     *
     * @param clienteCreateDTO clienteCreateDTO
     * @return {@link ClienteCreateDTO} clienteCreateDTO
     * @throws RegraDeNegocioException - Caso o clienteCreateDTO possua informacoes iguais a algum outro cliente que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     */
    @Operation(summary = "Criar cliente", description = "Cria um novo cliente")
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        ClienteDTO clienteDTO = clienteService.create(clienteCreateDTO);

        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    /**
     * Metodo para buscar uma lista com todos os Clientes existentes no Banco de dados
     *
     * @return {@link ClienteDTO} clienteDTO.
     */
    @Operation(summary = "Listar todos clientes", description = "Retorna uma lista com todos clientes")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> list() {
        return new ResponseEntity<>(clienteService.list(), HttpStatus.OK);
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
    @Operation(summary = "Buscar cliente por ID", description = "Retorna o cliente por ID")
    @GetMapping("/find-by-id")
    public ResponseEntity<ClienteDTO> findById(@RequestParam Integer idCliente) throws RegraDeNegocioException {
        ClienteDTO clienteDTO = clienteService.clienteFindById(idCliente);

        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    /**
     * Metodo para editar Cliente ja existente no Banco de dados
     *
     * @param clienteCreateDTO clienteCreateDTO
     * @param idCliente        Integer
     * @return {@link ClienteDTO} clienteDTO
     * @throws RegraDeNegocioException - Caso o clienteCreateDTO possua informacoes iguais a algum outro cliente que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     */
    @Operation(summary = "Atualiza cliente por ID", description = "Atualiza dados do cliente pelo ID inserido")
    @PutMapping("/update")
    public ResponseEntity<ClienteDTO> update(@RequestParam Integer idCliente, @RequestBody ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        ClienteDTO clienteDTO = clienteService.update(idCliente, clienteCreateDTO);

        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    /**
     * Metodo para deletar Cliente por ID existente no Banco de dados
     *
     * @param idCliente Integer
     * @return {@link Void} void
     * @throws RegraDeNegocioException - Caso o ID informado nao exista no banco de dados
     *                                 ele lança uma exceção, informando o motivo.
     */
    @Operation(summary = "Deletar cliente", description = "Deleta o cliente pelo ID informado")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Integer idCliente) throws RegraDeNegocioException {

        clienteService.delete(idCliente);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
