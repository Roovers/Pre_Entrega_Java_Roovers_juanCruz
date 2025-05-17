package com.techlab.app;

import java.util.Scanner;
import com.techlab.productos.ProductoService;
import com.techlab.pedidos.PedidoService;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService(productoService);

        int opcion;
        do {
            mostrarMenu();
            System.out.print("Elija una opción: ");
            opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1:
                    productoService.agregarProducto();
                    break;
                case 2:
                    productoService.listarProductos();
                    break;
                case 3:
                    productoService.actualizarProducto();
                    break;
                case 4:
                    productoService.eliminarProducto();
                    break;
                case 5:
                    pedidoService.crearPedido();
                    break;
                case 6:
                    pedidoService.listarPedidos();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

            System.out.println();

        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("========== SISTEMA DE GESTIÓN - TECHLAB ==========");
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar/Actualizar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("5) Crear un pedido");
        System.out.println("6) Listar pedidos");
        System.out.println("7) Salir");
    }

    private static int leerOpcion(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
