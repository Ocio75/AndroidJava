package com.example.aplicaiontpv.Objetos;

public class ArticuloComanda {
    private int cod_articulo ;
    private int cod_comanda ;
    private int cantidad ;

    public ArticuloComanda(int cod_articulo, int cod_comanda, int cantidad) {
        this.cod_articulo = cod_articulo;
        this.cod_comanda = cod_comanda;
        this.cantidad = cantidad;
    }

    public ArticuloComanda() {
    }

    public int getCod_articulo() {
        return cod_articulo;
    }

    public void setCod_articulo(int cod_articulo) {
        this.cod_articulo = cod_articulo;
    }

    public int getCod_comanda() {
        return cod_comanda;
    }

    public void setCod_comanda(int cod_comanda) {
        this.cod_comanda = cod_comanda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
