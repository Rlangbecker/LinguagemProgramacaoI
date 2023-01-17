package br.com.fundatec.dto;

import lombok.Data;

/**
 * <h1> Classe ClienteDTO</h1>
 *
 * <p>DTO (data transfer object) reponsavel pela transferencia de dados</p>
 * <p>Possui as seguintes propriedades: </p>
 * <ul>
 *    <li>idConta - Integer </li>
 *    <li>cpf - String </li>
 *    <li>nome - String </li>
 *
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @since 1.0
 */
@Data
public class ClienteDTO {

    private Integer idCliente;

    private String cpf;

    private String nome;
}
