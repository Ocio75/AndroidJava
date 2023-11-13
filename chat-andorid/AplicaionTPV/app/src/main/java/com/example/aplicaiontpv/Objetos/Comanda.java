package com.example.aplicaiontpv.Objetos;

public class Comanda {
    private int cod_comanda;
    private int dni;
    private String nota;

    public Comanda(int cod_comanda, int dni, String nota) {
        this.cod_comanda = cod_comanda;
        this.dni = dni;
        this.nota = nota;
    }

    public Comanda() {
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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
