package br.com.fundatec.dto;

import br.com.fundatec.model.TipoConta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ContaDTO {
    private Integer numero;
    private double saldo;
    private TipoConta tipoConta;
}
