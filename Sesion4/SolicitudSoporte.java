import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SolicitudSoporte {
    private String id;
    private String descripcion;
    private String prioridad; // "críticas", "importantes", "generales"
    public SolicitudSoporte siguiente;

    public SolicitudSoporte(String id, String descripcion, String prioridad) {
        this.id = id;
        this.descripcion = descripcion;
        this.prioridad = prioridad.toLowerCase();
        this.siguiente = null;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return prioridad + ": " + descripcion;
    }
    }
    
    class ListaEnlazadaSoporte {
        private SolicitudSoporte cabeza;
    
        public ListaEnlazadaSoporte() {
            this.cabeza = null;
        }
    
        // Método para agregar una solicitud según su prioridad
        public void agregarSolicitud(String id, String descripcion, String prioridad) {
            SolicitudSoporte nueva = new SolicitudSoporte(id, descripcion, prioridad);
    
            if (cabeza == null || prioridadEsMayor(nueva, cabeza)) {
                nueva.siguiente = cabeza;
                cabeza = nueva;
            } else {
                SolicitudSoporte temp = cabeza;
                while (temp.siguiente != null && !prioridadEsMayor(nueva, temp.siguiente)) {
                    temp = temp.siguiente;
                }
                nueva.siguiente = temp.siguiente;
                temp.siguiente = nueva;
            }
        }
    
        // Método para procesar la solicitud de mayor prioridad
        public SolicitudSoporte procesarSolicitud() {
            if (cabeza == null) {
                return null;
            }
            SolicitudSoporte procesada = cabeza;
            cabeza = cabeza.siguiente;
            return procesada;
        }
    
        // Mostrar todas las solicitudes pendientes
        public void mostrarSolicitudes() {
            SolicitudSoporte temp = cabeza;
            while (temp != null) {
                System.out.println("ID: " + temp.getId() + ", Descripción: " + temp.getDescripcion() + ", Prioridad: " + temp.getPrioridad());
                temp = temp.siguiente;
            }
        }
    
        // Evaluar si la prioridad de una solicitud es mayor a otra
        private boolean prioridadEsMayor(SolicitudSoporte nueva, SolicitudSoporte actual) {
            String[] niveles = {"críticas", "importantes", "generales"};
            int prioridadNueva = java.util.Arrays.asList(niveles).indexOf(nueva.getPrioridad());
            int prioridadActual = java.util.Arrays.asList(niveles).indexOf(actual.getPrioridad());
            return prioridadNueva < prioridadActual;
    }
    
    public class PruebaListaSoporte {
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
            ListaEnlazadaSoporte lista = new ListaEnlazadaSoporte();
            while (true) {
                System.out.println("1. Agregar solicitud");
                System.out.println("2. Procesar solicitud");
                System.out.println("3. Mostrar solicitudes pendientes");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
    
                if (opcion == 1) {
                    System.out.print("Ingrese el ID de la solicitud: ");
                    String id = scanner.nextLine();
                    System.out.print("Ingrese la descripción de la solicitud: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese la prioridad de la solicitud (críticas, importantes, generales): ");
                    String prioridad = scanner.nextLine();
                    lista.agregarSolicitud(id, descripcion, prioridad);
                     } else if (opcion == 2) {
                SolicitudSoporte solicitudProcesada = lista.procesarSolicitud();
                if (solicitudProcesada != null) {
                    System.out.println("Solicitud procesada: ID: " + solicitudProcesada.getId() + ", Descripción: " + solicitudProcesada.getDescripcion());
                } else {
                    System.out.println("No hay solicitudes pendientes.");
                }
            } else if (opcion == 3) {
                System.out.println("Solicitudes pendientes:");
                lista.mostrarSolicitudes();
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
    
