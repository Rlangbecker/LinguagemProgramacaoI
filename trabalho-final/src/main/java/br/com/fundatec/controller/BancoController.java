package br.com.fundatec.controller;


import br.com.fundatec.dto.BancoCreateDTO;
import br.com.fundatec.dto.BancoDTO;
import br.com.fundatec.service.BancoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/banco")
public class BancoController {


private final BancoService bancoService;

    @PostMapping
    public ResponseEntity<BancoDTO> create(@RequestBody BancoCreateDTO bancoCreateDTO){

       BancoDTO bancoDTO = bancoService.create(bancoCreateDTO);

        return new ResponseEntity<>(bancoDTO, HttpStatus.OK);
    }
}
