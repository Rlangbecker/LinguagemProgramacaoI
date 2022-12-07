package service;

import entity.Passageiro;
import service.enums.TipoPassagem;

import java.util.List;
import java.util.Random;

public abstract class Bilhete {
    public long id;
    public int numero;
    public String assento;

    public Passageiro passageiro;

    public Bilhete(long id, int numero, String assento,Passageiro passageiro) {
        this.id = id;
        this.numero = numero;
        this.assento = assento;
        this.passageiro = passageiro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }


    public void reservar(Passageiro passageiro){

    }

    public void comprar(){

    }

    public void cancelarReserva(){

    }

    public abstract double valor();

    public abstract int maximoBagagens();

    public static Bilhete verificarIdBilhete(List<Bilhete> bilhetes, Bilhete bilhete) {
        Random random = new Random();
        for (Bilhete bilheteTemporario : bilhetes) {
            while (bilheteTemporario.id == bilhete.id || bilheteTemporario.getNumero() == bilhete.getNumero() || bilheteTemporario.getAssento().equals(bilhete.getAssento())) {
                if (bilheteTemporario.id == bilhete.id) {
                    bilhete.setId(random.nextInt());
                }
                if(bilheteTemporario.getNumero()== bilhete.getNumero()){
                    bilhete.setId(random.nextInt(10000));
                }
                if(bilheteTemporario.assento.equals(bilhete.getAssento())){
                    bilhete.setAssento(random.ints(1,44).toString());
                }
            }
        }
        return bilhete;
    }
}
