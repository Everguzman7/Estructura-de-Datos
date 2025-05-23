import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Torres {
    public static void OrdenarTorres(int[] torres) {
        int n = torres.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) { // Optimización del bucle interno
                if (torres[j] > torres[j + 1]) {
                    int aux = torres[j];
                    torres[j] = torres[j + 1];
                    torres[j + 1] = aux;
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

        System.out.print("Ingrese la cantidad de torres: ");
        int n = scanner.nextInt();
        int[] torres = new int[n];

        System.out.println("Ingrese la cantidad de señal:");
        for (int i = 0; i < n; i++) {
            torres[i] = scanner.nextInt();
        }

        OrdenarTorres(torres);

        System.out.println("Torres ordenadas por intensidad de señal:");
        for (int i = 0; i < n; i++) {
            System.out.println(torres[i] + " ");
        }

        scanner.close();
    }
}