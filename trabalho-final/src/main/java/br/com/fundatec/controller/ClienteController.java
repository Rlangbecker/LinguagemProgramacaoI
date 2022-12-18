package br.com.fundatec.controller;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.ClienteCreateDTO;
import br.com.fundatec.dto.ClienteDTO;
import br.com.fundatec.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Criar cliente", description = "Cria um novo cliente")
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteCreateDTO clienteCreateDTO) {
        ClienteDTO clienteDTO = clienteService.create(clienteCreateDTO);

        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }
    @Operation(summary = "Listar todos clientes", description = "Retorna uma lista com todos clientes")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> list(){
        return new ResponseEntity<>(clienteService.list(),HttpStatus.OK);
    }

    @Operation(summary = "Buscar cliente por ID", description = "Retorna o cliente por ID")
    @GetMapping("/find-by-id")
    public ResponseEntity<ClienteDTO> findById(@RequestParam Integer idCliente) throws RegraDeNegocioException {
        ClienteDTO clienteDTO = clienteService.clienteFindById(idCliente);

        return new ResponseEntity<>(clienteDTO,HttpStatus.OK);
    }

    @Operation(summary = "Atualiza cliente por ID", description = "Atualiza dados do cliente pelo ID inserido")
    @PutMapping("/update")
    public ResponseEntity<ClienteDTO> update (@RequestParam Integer idCliente, @RequestBody ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        ClienteDTO clienteDTO = clienteService.update(idCliente,clienteCreateDTO);

        return new ResponseEntity<>(clienteDTO,HttpStatus.OK);
    }


    @Operation(summary = "Deletar cliente", description = "Deleta o cliente pelo ID informado")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Integer idCliente) throws RegraDeNegocioException {

        clienteService.delete(idCliente);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
