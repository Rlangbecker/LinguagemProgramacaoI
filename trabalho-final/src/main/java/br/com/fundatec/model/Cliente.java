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
    private Integer id;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "conta_id",referencedColumnName = "id")
    @JsonIgnoreProperties("cliente")
    private Conta conta;

}
