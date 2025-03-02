import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Credito {
    public static void OrdenarClientes(int[] clientes) {
        int n = clientes.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) { // Optimización del bucle interno
                if (clientes[j] > clientes[j + 1]) {
                    int aux = clientes[j];
                    clientes[j] = clientes[j + 1];
                    clientes[j + 1] = aux;
                }
            }
        }
    }


    public static void main(String[] args) {

    // Datos del encabezado
        String nombre = "Ever Rodriguez";
        String campus = "Campus Cali, U. Cooperativa de Colombia";
        String repositorioGit = " ";

        // Obtener la fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaHora = ahora.format(formateador);

        // Imprimir el encabezado
        System.out.println("+----------------------------------------");
        System.out.println("| 👤 Nombre: " + nombre);
        System.out.println("| 🎓 Campus: " + campus);
        System.out.println("| 📅 Fecha y hora: " + fechaHora);
        System.out.println("| 📂 Repositorio Git: " + repositorioGit);
        System.out.println("+----------------------------------------");
        System.out.println();
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de clientes: ");
        int n = scanner.nextInt();
        int[] clientes = new int[n];

        System.out.println("Ingrese el monto de egresos de los clientes:");
        for (int i = 0; i < n; i++) {
            clientes[i] = scanner.nextInt();
        }

        Credito.OrdenarClientes(clientes);

        System.out.println("Clientes ordenados por monto de egresos :");
        for (int i = 0; i < n; i++) {
            System.out.println(clientes[i] + " ");
        }

        scanner.close();
    }

}
    

