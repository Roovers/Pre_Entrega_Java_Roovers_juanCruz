package com.techlab.productos;

public class Comida extends Producto {
    private String tipoComida;

    public Comida(String nombre, double precio, int stock, String tipoComida) {
        super(nombre, precio, stock);
        this.tipoComida = tipoComida;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: " + tipoComida;
    }
}
