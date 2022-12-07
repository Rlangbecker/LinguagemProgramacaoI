package service;

import entity.Passageiro;

public class Economica extends Bilhete{

    public Economica(long id, int numero, String assento, Passageiro passageiroEconomico1) {
        super(id, numero, assento,passageiroEconomico1);
    }

    @Override
    public void reservar(Passageiro passageiro) {

        super.reservar(passageiro);
    }

    @Override
    public double valor() {
        return 100;
    }

    @Override
    public int maximoBagagens() {
        return 1;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
