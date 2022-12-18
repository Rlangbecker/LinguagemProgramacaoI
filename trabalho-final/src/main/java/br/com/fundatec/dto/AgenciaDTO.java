package br.com.fundatec.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgenciaDTO {

    private Integer idAgencia;

    private Integer numero;

    private String nome;

    private BancoDTO bancoDTO;
}
