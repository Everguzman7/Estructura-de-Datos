import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Turno {
    String nombre;
    Turno siguiente;

    public Turno(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }

    public String getNombre() {
        return nombre;
    }

    public static class ListaTurnos {
        private Turno cabeza;
    
        public ListaTurnos() {
            this.cabeza = null;
        }
    
        // Agregar un turno al final de la lista
        public void agregarTurno(String nombre) {
            Turno nuevoTurno = new Turno(nombre);
            if (cabeza == null) {
                cabeza = nuevoTurno;
            } else {
                Turno temp = cabeza;
                while (temp.siguiente != null) {
                    temp = temp.siguiente;
                }
                temp.siguiente = nuevoTurno;
            }
        }
    
        // Procesar el primer turno de la lista
        public Turno procesarTurno() {
            if (cabeza == null) {
                return null;
            }
            Turno procesado = cabeza;
            cabeza = cabeza.siguiente;
            return procesado;
        }
    
        // Mostrar todos los turnos pendientes
        public void mostrarTurnos() {
            Turno temp = cabeza;
            while (temp != null) {
                System.out.println("Turno: " + temp.getNombre());
                temp = temp.siguiente;
            }
        }
    }

    public class GestionTurnos {
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
         ListaTurnos listaTurnos = new ListaTurnos();

         while (true) {
            System.out.println("1. Agregar turno");
            System.out.println("2. Procesar turno");
            System.out.println("3. Mostrar turnos pendientes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea
             if (opcion == 1) {
                System.out.print("Ingrese el nombre del paciente: ");
                String nombrePaciente = scanner.nextLine();
                listaTurnos.agregarTurno(nombrePaciente);
            } else if (opcion == 2) {
                Turno turnoProcesado = listaTurnos.procesarTurno();
                if (turnoProcesado != null) {
                    System.out.println("Turno procesado: " + turnoProcesado.getNombre());
                } else {
                    System.out.println("No hay turnos pendientes.");
                }
            } else if (opcion == 3) {
                System.out.println("Turnos pendientes:");
                listaTurnos.mostrarTurnos();
            } else if (opcion == 4) {
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }


    }
}
         

    
    