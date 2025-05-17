package com.techlab.pedidos;

import com.techlab.productos.Producto;
import com.techlab.productos.ProductoService;
import com.techlab.excepciones.StockInsuficienteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoService {
    private List<Pedido> pedidos;
    private Scanner scanner;
    private ProductoService productoService;

    public PedidoService(ProductoService productoService) {
        this.productoService = productoService;
        this.pedidos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void crearPedido() {
        Pedido nuevoPedido = new Pedido();

        System.out.print("¿Cuántos productos desea agregar al pedido?: ");
        int cantidadProductos = leerIntPositivo();

        for (int i = 0; i < cantidadProductos; i++) {
            System.out.print("Ingrese ID del producto #" + (i + 1) + ": ");
            int idProducto = leerIntPositivo();

            Producto producto = productoService.buscarPorId(idProducto);
            if (producto == null) {
                System.out.println("Producto no encontrado. Intente nuevamente.");
                i--;
                continue;
            }

            System.out.print("Ingrese cantidad deseada para '" + producto.getNombre() + "': ");
            int cantidadDeseada = leerIntPositivo();

            try {
                verificarStock(producto, cantidadDeseada);
                producto.setStock(producto.getStock() - cantidadDeseada);
                LineaPedido linea = new LineaPedido(producto, cantidadDeseada);
                nuevoPedido.agregarLinea(linea);
                System.out.println("Producto agregado al pedido.");
            } catch (StockInsuficienteException e) {
                System.out.println(e.getMessage());
                i--;
            }
        }

        pedidos.add(nuevoPedido);
        System.out.println("\nPedido creado con éxito:");
        System.out.println(nuevoPedido);
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No se han realizado pedidos.");
            return;
        }

        System.out.println("\n--- Lista de Pedidos ---");
        for (Pedido p : pedidos) {
            System.out.println(p);
            System.out.println("-------------------------------------");
        }
    }

    private void verificarStock(Producto producto, int cantidad) throws StockInsuficienteException {
        if (cantidad > producto.getStock()) {
            throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
        }
    }

    private int leerIntPositivo() {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor > 0) return valor;
                else System.out.print("Debe ser un número positivo. Reintente: ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Reintente con un número entero: ");
            }
        }
    }
}
