import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Lunar {
    public static int maximo(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
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
        
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el numero de temperaturas  : ");
        int n = sc.nextInt(); // Corrección: usar 'sc' en lugar de 'Scanner'
        int[] array = new int[n];

        System.out.println("Introduce las temperaturas registradas  : ");
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt(); // Corrección: usar 'sc' en lugar de 'Scanner'
        }

        System.out.println("La mayor temperatura en la luna  es: " + maximo(array));

        sc.close(); // Corrección: cerrar el 'Scanner' correctamente
    }
}
