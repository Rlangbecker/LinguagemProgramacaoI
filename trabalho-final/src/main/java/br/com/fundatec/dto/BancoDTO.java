package br.com.fundatec.dto;

import lombok.Data;


@Data
public class BancoDTO {

    private Integer idBanco;
    private Integer codigo;
    private String nome;
    private String cnpj;
}
