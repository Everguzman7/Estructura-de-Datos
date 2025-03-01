import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Bancario {
    public static String obtenerPrimercliente(String[] Cliente) {
        return Cliente[0];
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

        System.out.print("Ingrese el numero de clientes: ");
        int n = scanner.nextInt();
        String[] Cliente = new String[n];
        System.out.println("Ingrese los nombres de los clientes:");
        for (int i = 0; i < n; i++) {
            System.out.println("Cliente " + (i + 1) + ": ");
            Cliente[i] = scanner.next();
        }
        System.out.println("El primer cliente en hacer una transaccion es: " + obtenerPrimercliente(Cliente));
        scanner.close();
    }
    
}
