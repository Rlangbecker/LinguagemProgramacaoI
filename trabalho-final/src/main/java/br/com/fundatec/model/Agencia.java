package br.com.fundatec.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * <h1> Classe Agencia</h1>
 *
 * <p> Classe responsavel por moldar a tabela Agencia no Banco de dados.</p>
 * <p>Possui as seguintes propriedades: </p>
 *  <ul>
 *     <li>idAgencia - Integer </li>
 *     <li>numero - Integer</li>
 *     <li>nome - String</li>
 *     <li>banco - Banco</li>
 *
 *  </ul>
 *
 * <p> Contem ligacao com as Entidades Banco e Conta, onde Banco tem agencias e a mesma possui contas.</p>
 * <ul>
 *     <li>Ligacao N-1 entre Agencia - Banco</li>
 *     <li>Ligacao 1-N entre Agencia - Conta</li>
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @see Conta
 * @see Banco
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "agencia")
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AGENCIA")
    private Integer idAgencia;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "nome")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_banco", updatable = false)
    @JsonIgnoreProperties("agencia")
    private Banco banco;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agencia", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("agencia")
    private Set<Conta> contas;
}
