package br.com.fundatec.controller;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.AgenciaCreateDTO;
import br.com.fundatec.dto.AgenciaDTO;
import br.com.fundatec.model.Agencia;
import br.com.fundatec.service.AgenciaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agencia")
public class AgenciaController {

    private final AgenciaService agenciaService;

    @Operation(summary = "Criar agencia", description = "Criar agencia")
    @PostMapping
    public ResponseEntity<AgenciaDTO> create (@RequestBody AgenciaCreateDTO agenciaCreateDTO) throws RegraDeNegocioException {
       AgenciaDTO agenciaDTO = agenciaService.create(agenciaCreateDTO);

       return new ResponseEntity<>(agenciaDTO, HttpStatus.OK);
    }

    @Operation(summary = "Listar agencias",description = "Retorna uma lista de todas as agencias do banco")
    @GetMapping
    public ResponseEntity<List<AgenciaDTO>> list(){
      List<AgenciaDTO> lista = agenciaService.list();
    return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    @Operation(summary = "Buscar agencia", description = "Retorna agencia pelo ID inserido")
    @GetMapping("/find-by-id")
    public ResponseEntity<AgenciaDTO> findById(@RequestParam Integer idAgencia) throws RegraDeNegocioException {
       AgenciaDTO agenciaDTO= agenciaService.agenciaFindById(idAgencia);

    return new ResponseEntity<>(agenciaDTO,HttpStatus.OK);
    }


    @Operation(summary = "Editar agencia", description = "Edita uma agencia pelo ID inserido")
    @PutMapping("/update")
    public ResponseEntity<AgenciaDTO> update (@RequestParam Integer idAgencia, @RequestBody AgenciaCreateDTO agenciaCreateDTO) throws RegraDeNegocioException {
      AgenciaDTO agenciaDTO = agenciaService.update(idAgencia,agenciaCreateDTO);

    return new ResponseEntity<>(agenciaDTO,HttpStatus.OK);
    }

    @Operation(summary = "Deletar agencia",description = "Deleta uma agencia pelo ID inserido")
    @DeleteMapping
    public ResponseEntity<Void> delete (@RequestParam Integer idAgencia) throws RegraDeNegocioException {
        agenciaService.delete(idAgencia);

        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
