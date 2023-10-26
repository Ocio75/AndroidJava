package com.example.aplicaiontpv.Objetos;

public class Articulo {
    private int cod_articulo ;
    private int stock ;
    private Double precio ;
    private byte[]Imagen ;

    public Articulo(int cod_articulo, int stock, Double precio, byte[] imagen) {
        this.cod_articulo = cod_articulo;
        this.stock = stock;
        this.precio = precio;
        Imagen = imagen;
    }

    public Articulo() {
    }

    public int getCod_articulo() {
        return cod_articulo;
    }

    public void setCod_articulo(int cod_articulo) {
        this.cod_articulo = cod_articulo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] imagen) {
        Imagen = imagen;
    }
}
