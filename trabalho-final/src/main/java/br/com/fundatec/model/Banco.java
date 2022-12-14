package br.com.fundatec.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer codigo;

    private String nome;

    private String cnpj;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "banco", cascade = CascadeType.ALL)
    private Set<Agencia> agencias;
}
