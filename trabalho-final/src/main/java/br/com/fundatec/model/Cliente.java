package br.com.fundatec.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * <h1> Classe Cliente</h1>
 *
 * <p> Classe responsavel por moldar a tabela Cliente no Banco de dados.</p>
 * <p>Possui as seguintes propriedades: </p>
 *  <ul>
 *     <li>idCliente - Integer </li>
 *     <li>cpf - String</li>
 *     <li>nome - String</li>
 *     <li>conta - Conta</li>
 *
 *  </ul>
 *
 * <p> Contem ligacao com as Entidade Conta, onde Cliente tem conta e Conta possui cliente.</p>
 * <ul>
 *     <li>Ligacao 1-1 entre Cliente - Conta</li>
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @see Conta
 * @see TipoConta
 * @since 1.0
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Integer idCliente;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cliente")
    private Conta conta;

}
