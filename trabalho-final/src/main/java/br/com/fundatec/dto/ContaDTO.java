package br.com.fundatec.dto;

import br.com.fundatec.model.Agencia;
import br.com.fundatec.model.TipoConta;
import lombok.Data;

@Data
public class ContaDTO {

    private Integer idConta;
    private ClienteDTO cliente;
    private AgenciaDTO agencia;
    private Integer numero;
    private double saldo;
    private TipoConta tipoConta;
}
