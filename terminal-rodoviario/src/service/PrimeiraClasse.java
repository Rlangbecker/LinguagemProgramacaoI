package service;

public class PrimeiraClasse extends Bilhete{


    public PrimeiraClasse(long id, int numero, String assento) {
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
