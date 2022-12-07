package entity;

import java.time.LocalDate;

public class Passageiro implements Pessoa{

    public String nome;
    public String endereco;
    public LocalDate dataNascimento;
    public boolean temReserva;

    public Passageiro(String nome, String endereco, LocalDate dataNascimento) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.temReserva = false;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTemReserva(boolean temReserva) {
        this.temReserva = temReserva;
    }

    public Boolean possuiReserva(){
        return temReserva;
    }

    @Override
    public String documentoPadrao() {
        return null;
    }

    @Override
    public String toString() {
        return "Passageiro [ " +
                "Nome: " + nome + " | " +
                "Endereco: " + endereco + " | " +
                "Data Nascimento: " + dataNascimento +
                " ]";
    }


}
