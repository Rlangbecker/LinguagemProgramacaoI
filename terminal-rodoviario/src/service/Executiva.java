package service;

import entity.Passageiro;

public class Executiva extends Bilhete{

    public Executiva(long id, int numero, String assento) {
        super(id, numero, assento);
    }

    @Override
    public double valor() {
        return 0;
    }

    @Override
    public int maximoBagagens() {
        return 0;
    }
}
