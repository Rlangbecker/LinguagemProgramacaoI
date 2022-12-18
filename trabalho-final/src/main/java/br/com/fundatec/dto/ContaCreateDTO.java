package br.com.fundatec.dto;

import br.com.fundatec.model.TipoConta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ContaCreateDTO {

    @NotNull
    private Integer idCliente;

    @NotNull
    private Integer idAgencia;

    @Schema(example = "5698746")
    private Integer numero;

    @Schema(example = "5000")
    private double saldo;

    private TipoConta tipoConta;
}
