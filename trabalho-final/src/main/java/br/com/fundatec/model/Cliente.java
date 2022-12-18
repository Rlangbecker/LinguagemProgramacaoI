package br.com.fundatec.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @OneToOne(fetch =FetchType.LAZY, cascade =CascadeType.ALL)
    @JsonIgnoreProperties("cliente")
    private Conta conta;

}
