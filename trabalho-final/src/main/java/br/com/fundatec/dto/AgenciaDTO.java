package br.com.fundatec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AgenciaDTO {

    private Integer id;

    private Integer numero;

    private String nome;
}
