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

    @Column(name ="nome")
    private String nome;

    @Column(name = "cnpj")
    private String cnpj;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "banco", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("banco")
    private Set<Agencia> agencias;
}
