package br.com.fundatec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClienteCreateDTO {

    @Schema(example = "04458632588")
    private String cpf;

    @Schema(example = "Cailan")
    private String nome;
}
