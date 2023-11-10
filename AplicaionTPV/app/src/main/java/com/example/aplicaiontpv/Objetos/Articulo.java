package com.example.aplicaiontpv.Objetos;

public class Articulo {
    private int cod_articulo ;
    private int stock ;
    private String tipo;
    private String nombre;
    private Double precio ;
    private byte[]Imagen ;

    public Articulo(int stock, String tipo, String nombre, Double precio, byte[] imagen) {
        this.stock = stock;
        this.tipo = tipo;
        this.nombre = nombre;
        this.precio = precio;
        Imagen = imagen;
    }
    public Articulo(int cod_articulo, int stock, String tipo, String nombre, Double precio, byte[] imagen) {
        this.cod_articulo = cod_articulo;
        this.stock = stock;
        this.tipo = tipo;
        this.nombre = nombre;
        this.precio = precio;
        Imagen = imagen;
    }

    public Articulo() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
