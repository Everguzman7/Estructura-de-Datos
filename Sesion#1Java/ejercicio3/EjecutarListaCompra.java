import java.util.Scanner;

public class EjecutarListaCompra {
    public static void main(String[] args) {
        ListaCompras listaCompras = new ListaCompras();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Agregar elemento a la lista");
            System.out.println("2. Eliminar elemento de la lista");
            System.out.println("3. Imprimir lista de compras");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el elemento a agregar:");
                    String elementoAgregar = scanner.nextLine();
                    listaCompras.agregarElemento(elementoAgregar);
                    break;
                case 2:
                    System.out.println("Ingrese el elemento a eliminar:");
                    String elementoEliminar = scanner.nextLine();
                    listaCompras.eliminarElemento(elementoEliminar);
                    break;
                case 3:
                    listaCompras.imprimirLista();
                    break;
                case 4:
                    System.out.println("Adiós");
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }
    
}
