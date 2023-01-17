package br.com.fundatec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <h1> Classe ClienteCreateDTO</h1>
 *
 * <p>DTO (data transfer object) reponsavel pela transferencia de dados para criacao</p>
 * <p>Possui as seguintes propriedades: </p>
 * <ul>
 *    <li>cpf - String </li>
 *    <li>nome - String</li>
 *
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @since 1.0
 */
@Data
public class ClienteCreateDTO {

    @Schema(example = "04458632588")
    private String cpf;

    @Schema(example = "Cailan")
    private String nome;
}
