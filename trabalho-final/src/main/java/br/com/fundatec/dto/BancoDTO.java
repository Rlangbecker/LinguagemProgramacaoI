package br.com.fundatec.dto;

import lombok.Data;

/**
 * <h1> Classe ContaDTO</h1>
 *
 * <p>DTO (data transfer object) reponsavel pela transferencia de dados</p>
 * <p>Possui as seguintes propriedades: </p>
 * <ul>
 *    <li>idBanco - Integer </li>
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
public class BancoDTO {

    private Integer idBanco;

    private Integer codigo;

    private String nome;

    private String cnpj;
}
