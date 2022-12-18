package br.com.fundatec.controller;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.ContaCreateDTO;
import br.com.fundatec.dto.ContaDTO;
import br.com.fundatec.model.Conta;
import br.com.fundatec.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaDTO> create(@RequestBody ContaCreateDTO contaCreateDTO) throws RegraDeNegocioException {
       ContaDTO contaDTO= contaService.create(contaCreateDTO);

       return new ResponseEntity<>(contaDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> list(){
        return new ResponseEntity<>(contaService.list(),HttpStatus.OK);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<ContaDTO> findById(@RequestParam Integer idConta) throws RegraDeNegocioException {
      ContaDTO contaDTO = contaService.contaFindById(idConta);
    return new ResponseEntity<>(contaDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ContaDTO> update(@RequestParam Integer idConta,@RequestBody ContaCreateDTO contaCreateDTO) throws RegraDeNegocioException {
        ContaDTO contaDTO =contaService.update(idConta,contaCreateDTO);
    return new ResponseEntity<>(contaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Integer idConta) throws RegraDeNegocioException {
        contaService.delete(idConta);
    return new ResponseEntity<>(null,HttpStatus.OK);
    }

}
