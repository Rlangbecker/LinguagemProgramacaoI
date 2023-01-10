package br.com.fundatec.model;

/**
 * <h1> ENUM TipoConta</h1>
 *
 * <p> Enum responsavel pela atribuicao de valor na Classe Cliente, no atributo tipoConta.</p>
 * <p>Possui apenas duas constantes: </p>
 * <ul>
 *    <li>POUPANCA - Com uma atribuicao de 1 para ser armazenado no banco em forma de char </li>
 *    <li>CORRENTE - Com uma atribuicao de 2 para ser armazenado no banco em forma de char </li>
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @see Cliente
 * @since 1.0
 */
public enum TipoConta {

    POUPANCA(1),
    CORRENTE(2);

    private Integer conta;

    TipoConta(Integer conta) {
        this.conta = conta;
    }

    public Integer getConta() {
        return conta;
    }

}
