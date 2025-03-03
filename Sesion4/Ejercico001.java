import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ejercico001 {

    public static class Transaccion {

        private String id;

        private double monto;

        private String tipo; // "deposito" o "retiro"

        public Transaccion siguiente;

        public Transaccion(String id, double monto, String tipo) {

            this.id = id;

            this.monto = monto;

            this.tipo = tipo;

            this.siguiente = null;

        }

        public String getId() {

            return id;

        }

        public double getMonto() {

            return monto;

        }

        public String getTipo() {

            return tipo;

        }

    }

    public static class ListaEnlazadaTransacciones {

        private Transaccion cabeza;

        public ListaEnlazadaTransacciones() {

            this.cabeza = null;

        }

        // Agregar una transacción al final de la lista

        public void agregarTransaccion(String id, double monto, String tipo) {

            Transaccion nueva = new Transaccion(id, monto, tipo);

            if (cabeza == null) {

                cabeza = nueva;

            } else {

                Transaccion temp = cabeza;

                while (temp.siguiente != null) {

                    temp = temp.siguiente;

                }

                temp.siguiente = nueva;

            }

        }

        // Procesar la primera transacción de la lista

        public Transaccion procesarTransaccion() {

            if (cabeza == null) {

                return null;

            }

            Transaccion procesada = cabeza;

            cabeza = cabeza.siguiente;

            return procesada;

        }

        // Mostrar todas las transacciones pendientes

        public void mostrarTransacciones() {

            Transaccion temp = cabeza;

            while (temp != null) {

                System.out.println("ID: " + temp.getId() + ", Monto: " + temp.getMonto() + ", Tipo: " + temp.getTipo());

                temp = temp.siguiente;

            }

        }

    }

    public class PruebaListaEnlazada {

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


            ListaEnlazadaTransacciones lista = new ListaEnlazadaTransacciones();

            // Agregar transacciones

            lista.agregarTransaccion("T001", 500.0, "deposito");

            lista.agregarTransaccion("T002", 200.0, "retiro");

            lista.agregarTransaccion("T003", 1000.0, "deposito");

            // Mostrar transacciones pendientes

            System.out.println("Transacciones pendientes:");

            lista.mostrarTransacciones();

            // Procesar una transacción

            Transaccion procesada = lista.procesarTransaccion();

            System.out.println("\nTransacción procesada: ID: " + procesada.getId());

            // Mostrar transacciones pendientes después del procesamiento

            System.out.println("\nTransacciones pendientes:");

            lista.mostrarTransacciones();

        }

    }
}