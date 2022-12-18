package br.com.fundatec.controller;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.ContaCreateDTO;
import br.com.fundatec.dto.ContaDTO;
import br.com.fundatec.model.Conta;
import br.com.fundatec.model.TipoConta;
import br.com.fundatec.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Criar nova Conta", description = "Cria uma nova conta")
    @PostMapping
    public ResponseEntity<ContaDTO> create(@RequestParam TipoConta tipoConta, @RequestBody ContaCreateDTO contaCreateDTO) throws RegraDeNegocioException {
       ContaDTO contaDTO= contaService.create(tipoConta,contaCreateDTO);

       return new ResponseEntity<>(contaDTO, HttpStatus.OK);
    }

    @Operation(summary = "Listar todas contas", description = "Retorna uma lista com todas contas")
    @GetMapping
    public ResponseEntity<List<ContaDTO>> list(){
        return new ResponseEntity<>(contaService.list(),HttpStatus.OK);
    }

    @Operation(summary = "Buscar conta por ID", description = "Retorna uma conta pelo ID inserido")
    @GetMapping("/find-by-id")
    public ResponseEntity<ContaDTO> findById(@RequestParam Integer idConta) throws RegraDeNegocioException {
      ContaDTO contaDTO = contaService.contaFindById(idConta);
    return new ResponseEntity<>(contaDTO, HttpStatus.OK);
    }


    @Operation(summary = "Listar todas contas pelo TIPO", description = "Retorna uma lista com todas contas pelo TIPO")
    @GetMapping("/list-by-tipo-conta")
    public ResponseEntity<List<ContaDTO>> listByTipoConta(@RequestParam TipoConta tipoConta) throws RegraDeNegocioException {
        List<ContaDTO> listByTipo = contaService.listByTipo(tipoConta);
        return new ResponseEntity<>(listByTipo, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar conta", description = "Atualiza dados da conta pelo ID informado")
    @PutMapping("/update")
    public ResponseEntity<ContaDTO> update(@RequestParam Integer idConta,@RequestParam TipoConta tipoConta,@RequestBody ContaCreateDTO contaCreateDTO) throws RegraDeNegocioException {
        ContaDTO contaDTO =contaService.update(idConta,contaCreateDTO,tipoConta);
    return new ResponseEntity<>(contaDTO, HttpStatus.OK);
    }

    @Operation(summary = "Deletar conta", description = "Deleta conta pelo ID informado")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Integer idConta) throws RegraDeNegocioException {
        contaService.delete(idConta);
    return new ResponseEntity<>(null,HttpStatus.OK);
    }

}
