package com.techlab.productos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductoService {
    private List<Producto> productos;
    private Scanner scanner;

    public ProductoService() {
        productos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void agregarProducto() {
        System.out.println("Seleccione tipo de producto:");
        System.out.println("1) Genérico");
        System.out.println("2) Bebida");
        System.out.println("3) Comida");
        System.out.print("Opción: ");

        int opcion = leerIntEnRango(1, 3);

        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese precio del producto: ");
        double precio = leerDoublePositivo();

        System.out.print("Ingrese cantidad en stock: ");
        int stock = leerIntNoNegativo();

        Producto producto;

        switch (opcion) {
            case 2: // Bebida
                System.out.print("Ingrese los litros: ");
                double litros = leerDoublePositivo();
                producto = new Bebida(nombre, precio, stock, litros);
                break;
            case 3: // Comida
                System.out.print("Ingrese tipo de comida (Ej: Snack, Fruta, Verdura): ");
                String tipoComida = scanner.nextLine();
                producto = new Comida(nombre, precio, stock, tipoComida);
                break;
            default: // Genrico
                producto = new Producto(nombre, precio, stock);
        }

        productos.add(producto);
        System.out.println("Producto agregado correctamente:\n" + producto);
    }

    private int leerIntEnRango(int min, int max) {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor >= min && valor <= max) return valor;
                else System.out.print("Opción inválida. Ingrese un número entre " + min + " y " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Reintente: ");
            }
        }
    }


    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println("\n--- Lista de Productos ---");
        for (Producto p : productos) {
            String tipo;

            if (p instanceof Bebida) tipo = "Bebida";
            else if (p instanceof Comida) tipo = "Comida";
            else tipo = "Genérico";

            System.out.println("[" + tipo + "] " + p);
        }
    }


    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Producto buscarPorNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre.trim())) {
                return p;
            }
        }
        return null;
    }

    public void actualizarProducto() {
        System.out.print("Ingrese el ID del producto a actualizar: ");
        int id = leerIntNoNegativo();
        Producto producto = buscarPorId(id);

        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Producto encontrado: " + producto);

        System.out.print("Nuevo precio (actual: " + producto.getPrecio() + "): ");
        double nuevoPrecio = leerDoublePositivo();
        producto.setPrecio(nuevoPrecio);

        System.out.print("Nuevo stock (actual: " + producto.getStock() + "): ");
        int nuevoStock = leerIntNoNegativo();
        producto.setStock(nuevoStock);

        System.out.println("Producto actualizado: " + producto);
    }

    public void eliminarProducto() {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = leerIntNoNegativo();
        Producto producto = buscarPorId(id);

        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("¿Estás seguro que deseas eliminar este producto? (s/n): ");
        String confirmacion = scanner.nextLine();
        if (confirmacion.equalsIgnoreCase("s")) {
            productos.remove(producto);
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    public List<Producto> getListaProductos() {
        return productos;
    }


    private double leerDoublePositivo() {
        while (true) {
            try {
                double valor = Double.parseDouble(scanner.nextLine());
                if (valor > 0) return valor;
                else System.out.print("Debe ser un valor positivo. Reingrese: ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Reingrese un número: ");
            }
        }
    }

    private int leerIntNoNegativo() {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor >= 0) return valor;
                else System.out.print("Debe ser un número no negativo. Reingrese: ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Reingrese un número entero: ");
            }
        }
    }
}
