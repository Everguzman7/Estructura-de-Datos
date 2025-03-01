import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Ganadero {
    public static int PesoGanado(int[] Ganado) {
        int suma = 0;
        for (int num : Ganado) {
            suma += num;
        }
        return suma;
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

        System.out.print("Ingrese el numero de reses : ");
        int n = scanner.nextInt();
        int[] Ganado = new int[n];

        System.out.println("Ingrese el peso del ganado :");
        for (int i = 0; i < n; i++) {
            Ganado[i] = scanner.nextInt();
        }

        System.out.println("El peso total del ganado es: " + PesoGanado(Ganado));
        scanner.close();
    }
    
}
