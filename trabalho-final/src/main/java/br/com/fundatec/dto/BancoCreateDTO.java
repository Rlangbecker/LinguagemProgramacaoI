package br.com.fundatec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class BancoCreateDTO {

    @NotNull
    @Schema(example = "123")
    private Integer codigo;

    @NotBlank
    @Schema(example = "FUNDATEC")
    private String nome;

    @NotBlank
    @Schema(example = "171252138")
    private String cnpj;
}
