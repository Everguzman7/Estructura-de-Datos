import java.util.Scanner;

class Paciente {
    String nombre;
    String diagnostico;

    Paciente(String nombre, String diagnostico) {
        this.nombre = nombre;
        this.diagnostico = diagnostico;
    }
}

public class Medico {
    public static int busquedaBinaria(Paciente[] arr, String target) {
        int izquierda = 0, derecha = arr.length - 1;
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            int comparacion = arr[medio].diagnostico.compareTo(target);
            if (comparacion == 0) return medio;
            if (comparacion < 0) izquierda = medio + 1;
            else derecha = medio - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de pacientes: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        Paciente[] pacientes = new Paciente[n];

        System.out.println("Ingrese los nombres y diagnósticos de los pacientes en orden ascendente de diagnóstico:");
        for (int i = 0; i < n; i++) {
            System.out.print("Nombre del paciente " + (i + 1) + ": ");
            String nombre = scanner.nextLine();
            System.out.print("Diagnóstico del paciente " + (i + 1) + ": ");
            String diagnostico = scanner.nextLine();
            pacientes[i] = new Paciente(nombre, diagnostico);
        }

        System.out.print("Ingrese el diagnóstico a buscar: ");
        String target = scanner.nextLine();

        int resultado = busquedaBinaria(pacientes, target);
        if (resultado != -1) {
            System.out.println("Paciente encontrado: " + pacientes[resultado].nombre + " con diagnóstico: " + pacientes[resultado].diagnostico);
        } else {
            System.out.println("Diagnóstico no encontrado.");
        }

        scanner.close();
    }
}