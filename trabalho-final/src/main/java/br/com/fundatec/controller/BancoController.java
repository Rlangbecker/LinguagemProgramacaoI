package br.com.fundatec.controller;


import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.BancoCreateDTO;
import br.com.fundatec.dto.BancoDTO;
import br.com.fundatec.service.BancoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/banco")
public class BancoController {


    private final BancoService bancoService;

    @Operation(summary = "Criar um banco", description = "Criar um banco")
    @PostMapping
    public ResponseEntity<BancoDTO> create(@RequestBody BancoCreateDTO bancoCreateDTO) {

        BancoDTO bancoDTO = bancoService.create(bancoCreateDTO);

        return new ResponseEntity<>(bancoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Listar todos os bancos", description = "Retorna uma lista com todos os bancos do banco de dadoss")
    @GetMapping
    public ResponseEntity<List<BancoDTO>> list() {

        List<BancoDTO> lista = bancoService.list();

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Operation(summary = "Procurar banco por ID", description = "Retorna o Banco pelo ID inserido")
    @GetMapping("/find-by-id")
    public ResponseEntity<BancoDTO> findById(@RequestParam Integer idBanco) throws RegraDeNegocioException {

        BancoDTO bancoDTO = bancoService.bancoFindById(idBanco);

        return new ResponseEntity<>(bancoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Editar banco",description = "Edita banco pelo ID inserido")
    @PutMapping("/update")
    public ResponseEntity<BancoDTO> update(@RequestParam Integer idBanco, @RequestBody BancoCreateDTO bancoCreateDTO) throws RegraDeNegocioException {

        BancoDTO bancoDTO = bancoService.update(idBanco, bancoCreateDTO);

        return new ResponseEntity<>(bancoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Deletar banco", description = "Deleta banco pelo ID inseridos")
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Integer idBanco) throws RegraDeNegocioException {
        bancoService.delete(idBanco);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
