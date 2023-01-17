package br.com.fundatec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <h1> Classe AgenciaUpdateDTO</h1>
 *
 * <p>DTO (data transfer object) reponsavel pela transferencia de dados para atualizar/editar</p>
 * <p>Possui as seguintes propriedades: </p>
 * <ul>
 *    <li>numero - Integer </li>
 *    <li>nome - String</li>
 *
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @since 1.0
 */
@Data
public class AgenciaUpdateDTO {

    @NotNull
    @Schema(example = "612583")
    private Integer numero;

    @NotBlank
    @Schema(example = "TI-21")
    private String nome;

}
