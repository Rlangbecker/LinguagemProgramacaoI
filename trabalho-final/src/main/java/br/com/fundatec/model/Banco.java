package br.com.fundatec.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * <h1> Classe Banco</h1>
 *
 * <p> Classe responsavel por moldar a tabela Banco no Banco de dados.</p>
 * <p>Possui as seguintes propriedades: </p>
 *  <ul>
 *     <li>idBanco - Integer </li>
 *     <li>codigo - Integer</li>
 *     <li>nome - String</li>
 *     <li>cnpj - String</li>
 *     <li>agencias - Set/Agencia</li>
 *
 *  </ul>
 *
 * <p> Contem ligacao com a Entidade Agencia, onde Banco possui agencias.</p>
 * <ul>
 *     <li>Ligacao 1-N entre Banco - Agencia</li>
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @see Agencia
 * @since 1.0
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "banco")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BANCO")
    private Integer idBanco;

    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cnpj")
    private String cnpj;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "banco", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("banco")
    private Set<Agencia> agencias;
}
