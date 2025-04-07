{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "cppdbg",
      "request": "launch",
      "name": "Debug C++ Program",
      "program": "${workspaceFolder}/${input:cppExecutable}",
      "args": [],
      "stopAtEntry": true,
      "cwd": "${workspaceFolder}",
      "environment": [],
      "externalConsole": false,
      "MIMode": "gdb",
      "setupCommands": [
        {
          "description": "Enable pretty-printing for gdb",
          "text": "-enable-pretty-printing",
          "ignoreFailures": true
        }
      ],
      "preLaunchTask": "C/C++: g++ compilar archivo activo"
    }
  ],
  "inputs": [
    {
      "type": "promptString",
      "id": "cppExecutable",
      "description": "Enter the relative path to the C++ executable you want to debug"
    }
  ]
}import java.util.Scanner;

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
