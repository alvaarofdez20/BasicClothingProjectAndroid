package com.example.basicclothingproject;


public class Products {
    private String referencia, nombre, tipo, talla;
    private Double precio;

    public Products(String referencia, String nombre, String tipo, Double precio, String talla) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.talla = talla;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
}
