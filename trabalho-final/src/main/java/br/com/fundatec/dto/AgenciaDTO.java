package br.com.fundatec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1> Classe ContaDTO</h1>
 *
 * <p>DTO (data transfer object) reponsavel pela transferencia de dados</p>
 * <p>Possui as seguintes propriedades: </p>
 * <ul>
 *    <li>idAgencia - Integer </li>
 *    <li>numero - Integer </li>
 *    <li>nome - String</li>
 *    <li>bancoDTO - BancoDTO</li>
 *
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgenciaDTO {

    private Integer idAgencia;

    private Integer numero;

    private String nome;

    private BancoDTO bancoDTO;
}
