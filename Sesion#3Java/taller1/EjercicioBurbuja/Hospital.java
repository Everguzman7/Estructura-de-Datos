import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Hospital {
    
    public static void OrdenarPacientes(int[] pacientes) {
        int n = pacientes.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) { // Optimización del bucle interno
                if (pacientes[j] > pacientes[j + 1]) {
                    int aux = pacientes[j];
                    pacientes[j] = pacientes[j + 1];
                    pacientes[j + 1] = aux;
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

        System.out.print("Ingrese la cantidad de pacientes: ");
        int n = scanner.nextInt();
        int[] pacientes = new int[n];

        System.out.println("Ingrese la edad de los pacientes:");
        for (int i = 0; i < n; i++) {
            System.out.print("Edad del paciente " + (i + 1) + ": ");
            pacientes[i] = scanner.nextInt();
        }

        OrdenarPacientes(pacientes);

        System.out.println("Pacientes ordenados por edad: ");
        for (int i = 0; i < n; i++) {
            System.out.println(pacientes[i] + " ");
        }

        scanner.close();
    }
}