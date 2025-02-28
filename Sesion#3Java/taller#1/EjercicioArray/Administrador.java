import java.util.Scanner;

public class Administrador {
    public static String obtenerPrimerPaciente(String[] Paciente) {
        return Paciente[0]; // Retorna el primer paciente
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el numero de pacientes: ");
        int n = scanner.nextInt();

        String[] Paciente = new String[n];
 
        System.out.println("Ingrese los nombres de los pacientes:");
        for (int i = 0; i < n; i++) {
            System.out.println("Paciente " + (i + 1) + ": ");
            Paciente[i] = scanner.next();
        
        }
 
        System.out.println("El primer elemento del array es: " + obtenerPrimerPaciente(Paciente));
        scanner.close();
    }

        

    

}
