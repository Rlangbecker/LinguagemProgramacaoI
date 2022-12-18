package br.com.fundatec.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
