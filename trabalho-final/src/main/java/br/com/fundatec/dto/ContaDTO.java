package br.com.fundatec.dto;

import br.com.fundatec.model.TipoConta;
import lombok.Data;

/**
 * <h1> Classe ContaDTO</h1>
 *
 * <p>DTO (data transfer object) reponsavel pela transferencia de dados</p>
 * <p>Possui as seguintes propriedades: </p>
 * <ul>
 *    <li>idConta - Integer </li>
 *    <li>cliente - ClienteDTO </li>
 *    <li>agencia - AgenciaDTO </li>
 *    <li>numero - Integer </li>
 *    <li>saldo - double</li>
 *    <li>tipoConta - TipoConta</li>
 *
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @since 1.0
 */
@Data
public class ContaDTO {

    private Integer idConta;

    private ClienteDTO cliente;

    private AgenciaDTO agencia;

    private Integer numero;

    private double saldo;

    private TipoConta tipoConta;
}
