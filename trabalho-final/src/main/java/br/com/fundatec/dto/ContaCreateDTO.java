package br.com.fundatec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <h1> Classe ContaCreateDTO</h1>
 *
 * <p>DTO (data transfer object) reponsavel pela transferencia de dados para criacao</p>
 * <p>Possui as seguintes propriedades: </p>
 * <ul>
 *    <li>idCliente - Integer </li>
 *    <li>idAgencia - Integer</li>
 *    <li>numero - Integer</li>
 *    <li>saldo - Double</li>
 *
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @since 1.0
 */
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
}
