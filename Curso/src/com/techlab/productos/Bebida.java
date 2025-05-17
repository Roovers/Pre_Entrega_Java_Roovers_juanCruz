package com.techlab.productos;

public class Bebida extends Producto {
    private double litros;

    public Bebida(String nombre, double precio, int stock, double litros) {
        super(nombre, precio, stock);
        this.litros = litros;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        if (litros > 0) {
            this.litros = litros;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Volumen: " + litros + " L";
    }
}
