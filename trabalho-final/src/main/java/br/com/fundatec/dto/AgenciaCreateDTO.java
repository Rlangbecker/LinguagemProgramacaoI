package br.com.fundatec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AgenciaCreateDTO {

    @NotNull
    @Schema(example = "612583")
    private Integer numero;

    @NotBlank
    @Schema(example = "Ricardo")
    private String nome;
}
