package br.com.fundatec.dto;

import br.com.fundatec.model.TipoConta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ContaCreateDTO {
    @Schema(example = "5698746")
    private Integer numero;
    @Schema(example = "5000")
    private double saldo;

    private TipoConta tipoConta;
}
