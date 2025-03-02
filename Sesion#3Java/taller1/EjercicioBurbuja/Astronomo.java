import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Astronomo {
    public static void OrdenarEstrellas(int[] estrellas) {
        int n = estrellas.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) { // Optimización del bucle interno
                if (estrellas[j] > estrellas[j + 1]) {
                    int aux = estrellas[j];
                    estrellas[j] = estrellas[j + 1];
                    estrellas[j + 1] = aux;
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

        System.out.print("Ingrese la cantidad de espectros de estrellas: ");
        int n = scanner.nextInt();
        int[] estrellas = new int[n];

        System.out.println("Ingrese la temperatura de las estrellas:");
        for (int i = 0; i < n; i++) {
            estrellas[i] = scanner.nextInt();
        }

        OrdenarEstrellas(estrellas);

        System.out.println("Estrellas ordenadas por temperatura para analizar su evolucion:");
        for (int i = 0; i < n; i++) {
            System.out.println(estrellas[i] + " ");
        }

        scanner.close();
    }
    
}
