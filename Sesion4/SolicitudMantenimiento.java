import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SolicitudMantenimiento {
    private String id;
    private String equipo;
    private String prioridad; // "alta", "media", "baja"
    public SolicitudMantenimiento siguiente;
 
    public SolicitudMantenimiento(String id, String equipo, String prioridad) {
        this.id = id;
        this.equipo = equipo;
        this.prioridad = prioridad.toLowerCase();
        this.siguiente = null;
    }
 
    public String getId() {
        return id;
    }
 
    public String getEquipo() {
        return equipo;
    }
 
    public String getPrioridad() {
        return prioridad;
    }

    public static class ListaEnlazadaMantenimiento {
        private SolicitudMantenimiento cabeza;
     
        public ListaEnlazadaMantenimiento() {
            this.cabeza = null;
        }
     
        // Método para agregar una solicitud según su prioridad
        public void agregarSolicitud(String id, String equipo, String prioridad) {
            SolicitudMantenimiento nueva = new SolicitudMantenimiento(id, equipo, prioridad);
     
            if (cabeza == null || prioridadEsMayor(nueva, cabeza)) {
                nueva.siguiente = cabeza;
                cabeza = nueva;
            } else {
                SolicitudMantenimiento temp = cabeza;
                while (temp.siguiente != null && !prioridadEsMayor(nueva, temp.siguiente)) {
                    temp = temp.siguiente;
                }
                nueva.siguiente = temp.siguiente;
                temp.siguiente = nueva;
            }
        }
     
        // Método para procesar la solicitud de mayor prioridad
        public SolicitudMantenimiento procesarSolicitud() {
            if (cabeza == null) {
                return null;
            }
            SolicitudMantenimiento procesada = cabeza;
            cabeza = cabeza.siguiente;
            return procesada;
        }
 
        // Mostrar todas las solicitudes pendientes
        public void mostrarSolicitudes() {
            SolicitudMantenimiento temp = cabeza;
            while (temp != null) {
                System.out.println("ID: " + temp.getId() + ", Equipo: " + temp.getEquipo() + ", Prioridad: " + temp.getPrioridad());
                temp = temp.siguiente;
            }
        }
     
        // Evaluar si la prioridad de una solicitud es mayor a otra
        private boolean prioridadEsMayor(SolicitudMantenimiento nueva, SolicitudMantenimiento actual) {
            String[] niveles = {"alta", "media", "baja"};
            int prioridadNueva = java.util.Arrays.asList(niveles).indexOf(nueva.getPrioridad());
            int prioridadActual = java.util.Arrays.asList(niveles).indexOf(actual.getPrioridad());
            return prioridadNueva < prioridadActual;
        }
    }

    public static class PruebaListaMantenimiento {
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




            ListaEnlazadaMantenimiento lista = new ListaEnlazadaMantenimiento();


     
            // Agregar solicitudes de mantenimiento con diferentes prioridades
            lista.agregarSolicitud("M001", "motbomba agua", "media");
            lista.agregarSolicitud("M002", "Generador Energia", "alta");
            lista.agregarSolicitud("M003", "Sistema de Aire Acondicionado", "baja");
            lista.agregarSolicitud("M004", "valvula de Gas", "alta");
     
            // Mostrar solicitudes pendientes
            System.out.println("Solicitudes pendientes:");
            lista.mostrarSolicitudes();
     
            // Procesar la solicitud más prioritaria
            SolicitudMantenimiento procesada = lista.procesarSolicitud();
            System.out.println("\nSolicitud procesada: ID: " + procesada.getId() + ", Equipo: " + procesada.getEquipo());
     
            // Mostrar solicitudes pendientes después del procesamiento
            System.out.println("\nSolicitudes pendientes:");
            lista.mostrarSolicitudes();
        }
    }
}    
    


    
