import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
 
public class Cientifico {
    public static int obtenerPrimeTemperatura(int[] Temperatura) {
        return Temperatura[0];

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
        
        System.out.print("Ingrese el numero de temperaturas: ");
        int n = scanner.nextInt();
        int[] Temperatura = new int[n];
 
        System.out.println("Ingrese el numero de grados de temperaturas registradas en el dia:");
        for (int i = 0; i < n; i++) {
            Temperatura[i] = scanner.nextInt();
        }
 
        System.out.println("la primer temperatura registada del dia es: " + obtenerPrimeTemperatura(Temperatura));

        scanner.close();
    }
}
