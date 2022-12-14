package br.com.fundatec.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private double saldo;

    @Enumerated(EnumType.ORDINAL)
    private TipoConta tipoConta;

    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "cliente_id",referencedColumnName = "id")
    @JsonIgnoreProperties("conta")

    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    private Agencia agencia;

}
