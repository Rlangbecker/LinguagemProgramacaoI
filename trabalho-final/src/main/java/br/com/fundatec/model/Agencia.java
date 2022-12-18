package br.com.fundatec.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "agencia")
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
    @JoinColumn(name="id_banco", updatable = false)
    @JsonIgnoreProperties("agencia")
    private Banco banco;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agencia", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("agencia")
    private Set<Conta> contas;
}
