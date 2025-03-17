import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Agricola {
    public static void OrdenarParcelas(int[] parcelas) {
        int n = parcelas.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) { // Optimización del bucle interno
                if (parcelas[j] > parcelas[j + 1]) {
                    int aux = parcelas[j];
                    parcelas[j] = parcelas[j + 1];
                    parcelas[j + 1] = aux;
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

        System.out.print("Ingrese la cantidad de parcelas en el cultivo : ");
        int n = scanner.nextInt();
        int[] parcelas = new int[n];

        System.out.println("Ingrese la fecha de siembra de las  parcelas:");
        for (int i = 0; i < n; i++) {
            parcelas[i] = scanner.nextInt();
        }

        Agricola.OrdenarParcelas(parcelas);

        System.out.println("Parcelas ordenadas por fecha: ");
        for (int i = 0; i < n; i++) {
            System.out.println(parcelas[i] + " ");
        }

        scanner.close();
    }

}
