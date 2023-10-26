package com.example.aplicaiontpv.Objetos;

public class Comanda {
    private int cod_comanda;
    private int dni;

      public Comanda(int cod_comanda, int dni) {
        this.cod_comanda = cod_comanda;
        this.dni = dni;
    }

    public Comanda() {
    }
    public int getCod_comanda() {
        return cod_comanda;
    }

    public void setCod_comanda(int cod_comanda) {
        this.cod_comanda = cod_comanda;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}
