import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Espacial {
    public static int EnergiaConsumida(int[] Energia) {
        int suma = 0;
        for (int num : Energia) {
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

        System.out.print("Ingrese el numero de dispositivos de modulos conectados: ");
        int n = scanner.nextInt();
        int[] Energia = new int[n];

        System.out.println("Ingrese el consumo de energia de los dispositivos:");
        for (int i = 0; i < n; i++) {
            Energia[i] = scanner.nextInt();
        }

        System.out.println("El consumo total de energia por los modules es: " + EnergiaConsumida(Energia));
        scanner.close();
    }
}
