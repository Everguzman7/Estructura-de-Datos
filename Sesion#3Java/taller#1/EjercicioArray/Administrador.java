<<<<<<< HEAD
=======
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
>>>>>>> 2218bc5 (Reemplazar archivo de algoritmos de búsqueda con nueva implementación)
import java.util.Scanner;

public class Administrador {
    public static String obtenerPrimerPaciente(String[] Paciente) {
        return Paciente[0]; // Retorna el primer paciente
    }
    public static void main(String[] args) {
<<<<<<< HEAD
=======

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

>>>>>>> 2218bc5 (Reemplazar archivo de algoritmos de búsqueda con nueva implementación)
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el numero de pacientes: ");
        int n = scanner.nextInt();

        String[] Paciente = new String[n];
 
        System.out.println("Ingrese los nombres de los pacientes:");
        for (int i = 0; i < n; i++) {
            System.out.println("Paciente " + (i + 1) + ": ");
            Paciente[i] = scanner.next();
        
        }
 
<<<<<<< HEAD
        System.out.println("El primer elemento del array es: " + obtenerPrimerPaciente(Paciente));
=======
        System.out.println("El primer paciente es: " + obtenerPrimerPaciente(Paciente));
>>>>>>> 2218bc5 (Reemplazar archivo de algoritmos de búsqueda con nueva implementación)
        scanner.close();
    }

        

    

}
