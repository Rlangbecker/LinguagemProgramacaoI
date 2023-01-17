package br.com.fundatec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <h1> Classe BancoCreateDTO</h1>
 *
 * <p>DTO (data transfer object) reponsavel pela transferencia de dados para criacao</p>
 * <p>Possui as seguintes propriedades: </p>
 * <ul>
 *    <li>codigo - Integer </li>
 *    <li>nome - String</li>
 *    <li>cnpj - String</li>
 *
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @since 1.0
 */
@Data
public class BancoCreateDTO {

    @NotNull
    @Schema(example = "123")
    private Integer codigo;

    @NotBlank
    @Schema(example = "FUNDATEC")
    private String nome;

    @NotBlank
    @Schema(example = "171252138")
    private String cnpj;
}
