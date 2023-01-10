package br.com.fundatec.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * <h1> Classe Conta</h1>
 *
 * <p> Classe responsavel por moldar a tabela Conta no Banco de dados.</p>
 * <p>Possui as seguintes propriedades: </p>
 *  <ul>
 *     <li>idConta - Integer </li>
 *     <li>numero - Integer</li>
 *     <li>saldo - Double</li>
 *     <li>tipoConta - TipoConta</li>
 *     <li>cliente - Cliente</li>
 *     <li>agencia - Agencia</li>
 *  </ul>
 *
 * <p> Contem ligacao com as Entidades Cliente e Agencia, onde Agencia tem Contas e a mesma possui clientes.</p>
 * <ul>
 *     <li>Ligacao N-1 entre Conta - Agencia</li>
 *     <li>Ligacao 1-1 entre Conta - Cliente</li>
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @see Conta
 * @see Cliente
 * @see TipoConta
 * @since 1.0
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTA")
    private Integer idConta;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "saldo")
    private double saldo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_conta")
    private TipoConta tipoConta;

    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    @JsonIgnoreProperties("conta")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agencia", updatable = false)
    private Agencia agencia;

}
