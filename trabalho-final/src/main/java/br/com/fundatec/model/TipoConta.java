package br.com.fundatec.model;

public enum TipoConta {

    POUPANCA(1),
    CORRENTE(2);

    private Integer conta;

    TipoConta(Integer conta){
        this.conta = conta;
    }

    public Integer getConta(){
        return conta;
    }

}
