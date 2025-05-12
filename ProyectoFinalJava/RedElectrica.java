import java.util.*;
import java.util.concurrent.*;

// Clase principal y única
public class RedElectrica {

    // ---------- Modelos ----------
    static class Nodo {
        String nombre;
        double consumo;

        Nodo(String nombre) {
            this.nombre = nombre;
            this.consumo = 0;
        }
    }

    static class Grafo {
        Map<String, Nodo> nodos = new HashMap<>();
        Map<String, Map<String, Double>> adyacencias = new HashMap<>();

        void agregarNodo(String nombre) {
            nodos.put(nombre, new Nodo(nombre));
            adyacencias.put(nombre, new HashMap<>());
        }

        void agregarArista(String origen, String destino, double peso) {
            if (!nodos.containsKey(origen)) {
                System.out.println("Error: El nodo de origen '" + origen + "' no existe en el grafo.");
                return;
            }
            if (!nodos.containsKey(destino)) {
                System.out.println("Error: El nodo de destino '" + destino + "' no existe en el grafo.");
                return;
            }
            adyacencias.get(origen).put(destino, peso);
        }

        void desconectar(String origen, String destino) {
            if (!nodos.containsKey(origen)) {
                System.out.println("Error: El nodo de origen '" + origen + "' no existe en el grafo.");
                return;
            }
            if (adyacencias.containsKey(origen)) {
                adyacencias.get(origen).remove(destino);
            }
        }

        Map<String, Double> getVecinos(String nodo) {
            return adyacencias.get(nodo);
        }

        Set<String> getNodos() {
            return nodos.keySet();
        }

        Nodo getNodo(String nombre) {
            return nodos.get(nombre);
        }

        Map<String, Map<String, Double>> getAdyacencias() {
            return adyacencias;
        }
    }

    // ---------- Historia 1: Dijkstra ----------
    static class Dijkstra {
        static Map<String, Double> calcularRuta(Grafo grafo, String inicio) {
            Map<String, Double> distancias = new HashMap<>();
            Set<String> visitados = new HashSet<>();
            PriorityQueue<String> cola = new PriorityQueue<>(Comparator.comparing(distancias::get));

            for (String nodo : grafo.getNodos()) distancias.put(nodo, Double.POSITIVE_INFINITY);
            distancias.put(inicio, 0.0);
            cola.add(inicio);

            while (!cola.isEmpty()) {
                String actual = cola.poll();
                if (!visitados.add(actual)) continue;

                Map<String, Double> vecinos = grafo.getVecinos(actual);
                if (vecinos != null) {
                    for (Map.Entry<String, Double> vecino : vecinos.entrySet()) {
                        double nuevaDistancia = distancias.get(actual) + vecino.getValue();
                        if (nuevaDistancia < distancias.get(vecino.getKey())) {
                            distancias.put(vecino.getKey(), nuevaDistancia);
                            cola.add(vecino.getKey());
                        }
                    }
                }
            }
            return distancias;
        }
    }

    // ---------- Historia 2: Floyd-Warshall ----------
    static class FloydWarshall {
        static double[][] calcularTodoPar(Grafo grafo, List<String> nodos) {
            int n = nodos.size();
            double[][] dist = new double[n][n];
            for (int i = 0; i < n; i++) Arrays.fill(dist[i], Double.POSITIVE_INFINITY);
            for (int i = 0; i < n; i++) dist[i][i] = 0;

            for (int i = 0; i < n; i++) {
                String u = nodos.get(i);
                Map<String, Double> vecinos = grafo.getVecinos(u);
                if (vecinos != null) {
                    for (Map.Entry<String, Double> e : vecinos.entrySet()) {
                        int j = nodos.indexOf(e.getKey());
                        if (j != -1) {
                            dist[i][j] = e.getValue();
                        }
                    }
                }
            }

            for (int k = 0; k < n; k++)
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        if (dist[i][k] != Double.POSITIVE_INFINITY && dist[k][j] != Double.POSITIVE_INFINITY && dist[i][k] + dist[k][j] < dist[i][j])
                            dist[i][j] = dist[i][k] + dist[k][j];

            return dist;
        }
    }

    // ---------- Historia 3: Bellman-Ford ----------
    static class BellmanFord {
        static boolean tieneCicloNegativo(Grafo grafo, String origen) {
            Map<String, Double> dist = new HashMap<>();
            for (String nodo : grafo.getNodos()) dist.put(nodo, Double.POSITIVE_INFINITY);
            dist.put(origen, 0.0);

            int numNodos = grafo.getNodos().size();
            for (int i = 0; i < numNodos - 1; i++) {
                for (String u : grafo.getNodos()) {
                    Map<String, Double> vecinos = grafo.getVecinos(u);
                    if (vecinos != null) {
                        for (Map.Entry<String, Double> v : vecinos.entrySet()) {
                            if (dist.get(u) != Double.POSITIVE_INFINITY && dist.get(u) + v.getValue() < dist.get(v.getKey())) {
                                dist.put(v.getKey(), dist.get(u) + v.getValue());
                            }
                        }
                    }
                }
            }

            for (String u : grafo.getNodos()) {
                Map<String, Double> vecinos = grafo.getVecinos(u);
                if (vecinos != null) {
                    for (Map.Entry<String, Double> v : vecinos.entrySet()) {
                        if (dist.get(u) != Double.POSITIVE_INFINITY && dist.get(u) + v.getValue() < dist.get(v.getKey())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    // ---------- Historia 4: Simulación Dinámica de Fallos ----------
    static class SimuladorFallos extends Thread {
        Grafo grafo;
        ColaAlertas alertas;
        private volatile boolean corriendo = true;

        SimuladorFallos(Grafo grafo, ColaAlertas alertas) {
            this.grafo = grafo;
            this.alertas = alertas;
        }

        public void detener() {
            corriendo = false;
            interrupt();
        }

        public void run() {
            Random rand = new Random();
            List<String> nodos = new ArrayList<>(grafo.getNodos());

            while (corriendo) {
                try {
                    Thread.sleep((rand.nextInt(5) + 3) * 1000);
                    if (grafo.getNodos().isEmpty()) continue;
                    int tipoFallo = rand.nextInt(3); // 0: desconectar, 1: aumentar pérdida, 2: fallo de nodo

                    if (tipoFallo == 0 && grafo.getAdyacencias().size() > 0) {
                        // Simular desconexión
                        String origen = nodos.get(rand.nextInt(nodos.size()));
                        Map<String, Double> vecinos = grafo.getVecinos(origen);
                        if (vecinos != null && !vecinos.isEmpty()) {
                            List<String> vecinosList = new ArrayList<>(vecinos.keySet());
                            String destino = vecinosList.get(rand.nextInt(vecinosList.size()));
                            grafo.desconectar(origen, destino);
                            String mensaje = "⚠ Fallo simulado: desconectado " + origen + " -> " + destino;
                            System.out.println(mensaje);
                            alertas.agregarAlerta(mensaje);
                            new ReconexionAutomatica(grafo, origen, destino, 5).start(); // Asumiendo peso 5 para la reconexión
                        }
                    } else if (tipoFallo == 1 && grafo.getAdyacencias().size() > 0) {
                        // Simular aumento de pérdida
                        String origen = nodos.get(rand.nextInt(nodos.size()));
                        Map<String, Double> vecinos = grafo.getVecinos(origen);
                        if (vecinos != null && !vecinos.isEmpty()) {
                            List<String> vecinosList = new ArrayList<>(vecinos.keySet());
                            String destino = vecinosList.get(rand.nextInt(vecinosList.size()));
                            double perdidaActual = vecinos.get(destino);
                            double incremento = rand.nextDouble() * 10; // Aumento aleatorio
                            grafo.agregarArista(origen, destino, perdidaActual + incremento);
                            String mensaje = "🔥 Fallo simulado: aumento de pérdida en " + origen + " -> " + destino + " (+" + String.format("%.2f", incremento) + ")";
                            System.out.println(mensaje);
                            alertas.agregarAlerta(mensaje);
                            // Podrías implementar un mecanismo para revertir el aumento después de un tiempo
                        }
                    } else if (tipoFallo == 2 && !nodos.isEmpty()) {
                        // Simular fallo de nodo
                        String nodoFallido = nodos.get(rand.nextInt(nodos.size()));
                        // Desconectar todas las aristas incidentes al nodo
                        Map<String, Map<String, Double>> adyacencias = grafo.getAdyacencias();
                        for (Map.Entry<String, Map<String, Double>> entry : new HashMap<>(adyacencias).entrySet()) {
                            if (entry.getKey().equals(nodoFallido)) {
                                adyacencias.put(entry.getKey(), new HashMap<>()); // Desconectar salientes
                            } else {
                                entry.getValue().remove(nodoFallido); // Desconectar entrantes
                            }
                        }
                        String mensaje = "💥 Fallo simulado: subestación " + nodoFallido + " fuera de servicio";
                        System.out.println(mensaje);
                        alertas.agregarAlerta(mensaje);
                        // Podrías implementar una lógica para "reactivar" el nodo después de un tiempo
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    corriendo = false;
                } catch (IllegalArgumentException e) {
                    // Manejar posibles excepciones si el grafo está vacío o no tiene aristas
                }
            }
            System.out.println("🛑 Simulación de fallos detenida.");
        }
    }

    // ---------- Historia 5: Cola de alertas ----------
    static class Alerta {
        String mensaje;
        Alerta(String mensaje) { this.mensaje = mensaje; }
        String getMensaje() { return mensaje; }
    }

    static class ColaAlertas {
        Queue<Alerta> cola = new ConcurrentLinkedQueue<>();
        void agregarAlerta(String mensaje) { cola.add(new Alerta(mensaje)); }
        Alerta procesarAlerta() { return cola.poll(); }
        boolean hayAlertas() { return !cola.isEmpty(); }
    }

    // ---------- Historia 6: Gestión automática de reintentos de reconexión ----------
    static class ReconexionAutomatica extends Thread {
        Grafo grafo;
        String origen, destino;
        double peso;

        ReconexionAutomatica(Grafo grafo, String origen, String destino, double peso) {
            this.grafo = grafo;
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }

        public void run() {
            int reintentos = 3;
            while (reintentos-- > 0) {
                try {
                    Thread.sleep(4000);
                    grafo.agregarArista(origen, destino, peso);
                    System.out.println("🔁 Reconectado " + origen + " -> " + destino);
                    break;
                } catch (InterruptedException e) {
                    System.out.println("❌ Error al reconectar (interrupción).");
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception e) {
                    System.out.println("❌ Error al reconectar.");
                }
            }
            if (reintentos < 0) {
                System.out.println("❌ No se pudo reconectar " + origen + " -> " + destino + " después de varios intentos.");
            }
        }
    }

    // ---------- Historia 7: Lista enlazada de nodos activos ----------
    static class ListaEnlazada {
        class NodoLista {
            Nodo nodo;
            NodoLista siguiente;
            NodoLista(Nodo nodo) { this.nodo = nodo; }
        }

        NodoLista cabeza = null;
        void agregar(Nodo nodo) {
            NodoLista nuevo = new NodoLista(nodo);
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        }

        void imprimir() {
            NodoLista actual = cabeza;
            while (actual != null) {
                System.out.println("🟢 Nodo activo: " + actual.nodo.nombre);
                actual = actual.siguiente;
            }
            if (cabeza == null) {
                System.out.println("⚠️ No hay nodos activos en la lista.");
            }
        }
    }

    // ---------- Historia 8: Simulación de generación/consumo de nodos ----------
    static class SimuladorConsumo extends Thread {
        Grafo grafo;
        private volatile boolean corriendo = true;

        SimuladorConsumo(Grafo grafo) {
            this.grafo = grafo;
        }

        public void detener() {
            corriendo = false;
            interrupt();
        }

        public void run() {
            Random rand = new Random();
            while (corriendo) {
                try {
                    Thread.sleep(5000);
                    for (String nombre : grafo.getNodos()) {
                        double consumo = rand.nextDouble() * 100;
                        grafo.getNodo(nombre).consumo = consumo;
                        System.out.println("🔄 " + nombre + " consumo actualizado: " + String.format("%.2f", consumo) + " kW");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    corriendo = false;
                }
            }
            System.out.println("🛑 Simulación de consumo detenida.");
        }
    }

    public static void mostrarMenu() {
        System.out.println("\n===== MENÚ DE GESTIÓN DE RED ELÉCTRICA =====");
        System.out.println("1. Calcular ruta de menor pérdida (Dijkstra)");
        System.out.println("2. Análisis global de la red (Floyd-Warshall)");
        System.out.println("3. Detectar pérdidas económicas negativas (Bellman-Ford)");
        System.out.println("4. Iniciar/Detener simulación de fallos");
        System.out.println("5. Iniciar/Detener simulación de consumo");
        System.out.println("6. Ver y procesar alertas");
        System.out.println("7. Ver nodos activos");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // ---------- Interfaz de usuario ----------

    public static void iniciarMenu(Grafo grafo) {
        Scanner scanner = new Scanner(System.in);
        ColaAlertas alertas = new ColaAlertas();
        ListaEnlazada lista = new ListaEnlazada();
        SimuladorFallos simuladorFallos = null;
        SimuladorConsumo simuladorConsumo = null;
        boolean fallosIniciados = false;
        boolean consumoIniciado = false;

        for (String nombre : grafo.getNodos())
            lista.agregar(grafo.getNodo(nombre));

        while (true) {
            mostrarMenu();
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1": // Calcular ruta de menor pérdida (Dijkstra)
                    System.out.print("Ingrese el nodo de inicio para calcular la ruta: ");
                    String inicioDijkstra = scanner.nextLine().toUpperCase();
                    if (grafo.getNodos().contains(inicioDijkstra)) {
                        Map<String, Double> distancias = Dijkstra.calcularRuta(grafo, inicioDijkstra);
                        System.out.println("\n🔍 Ruta mínima desde " + inicioDijkstra + ":");
                        distancias.forEach((k, v) -> System.out.println(" - " + inicioDijkstra + " -> " + k + ": " + String.format("%.2f", v)));
                    } else {
                        System.out.println("❌ El nodo '" + inicioDijkstra + "' no existe en el grafo.");
                    }
                    break;

                case "2": // Análisis global de red (Floyd-Warshall)
                    List<String> nodosFW = new ArrayList<>(grafo.getNodos());
                    if (!nodosFW.isEmpty()) {
                        double[][] fw = FloydWarshall.calcularTodoPar(grafo, nodosFW);
                        System.out.println("\n🌐 Matriz de distancias (Floyd-Warshall):");
                        System.out.print("    ");
                        for (String nodo : nodosFW) System.out.printf("%-5s ", nodo);
                        System.out.println();
                        for (int i = 0; i < nodosFW.size(); i++) {
                            System.out.printf("%-5s ", nodosFW.get(i));
                            for (int j = 0; j < nodosFW.size(); j++) {
                                System.out.printf("%-5.2f ", fw[i][j]);
                            }
                            System.out.println();
                        }
                    } else {
                        System.out.println("⚠️ El grafo está vacío, no se puede ejecutar Floyd-Warshall.");
                    }
                    break;

                case "3": // Detectar pérdidas económicas negativas (Bellman-Ford)
                    System.out.print("Ingrese el nodo de inicio para detectar ciclos negativos: ");
                    String inicioBellmanFord = scanner.nextLine().toUpperCase();
                    if (grafo.getNodos().contains(inicioBellmanFord)) {
                        boolean ciclo = BellmanFord.tieneCicloNegativo(grafo, inicioBellmanFord);
                        System.out.println("\n🔎 ¿Ciclo negativo detectado desde " + inicioBellmanFord + "? " + ciclo);
                        if (ciclo) {
                            System.out.println("⚠️ Posibles pérdidas anormales detectadas.");
                        }
                    } else {
                        System.out.println("❌ El nodo '" + inicioBellmanFord + "' no existe en el grafo.");
                    }
                    break;

                case "4": // Iniciar/Detener simulación de fallos
                    if (!fallosIniciados) {
                        simuladorFallos = new SimuladorFallos(grafo, alertas);
                        simuladorFallos.start();
                        fallosIniciados = true;
                        System.out.println("\n⚠ Simulación de fallos iniciada.");
                    } else {
                        if (simuladorFallos != null) {
                            simuladorFallos.detener();
                        }
                        fallosIniciados = false;
                        System.out.println("\n🛑 Simulación de fallos detenida.");
                    }
                    break;

                case "5": // Iniciar/Detener simulación de consumo
                    if (!consumoIniciado) {
                        simuladorConsumo = new SimuladorConsumo(grafo);
                        simuladorConsumo.start();
                        consumoIniciado = true;
                        System.out.println("\n🔄 Simulación de consumo iniciada.");
                    } else {
                        if (simuladorConsumo != null) {
                            simuladorConsumo.detener();
                        }
                        consumoIniciado = false;
                        System.out.println("\n🛑 Simulación de consumo detenida.");
                    }
                    break;

                case "6": // Ver y procesar alertas
                    System.out.println("\n🚨 --- Alertas del Sistema ---");
                    if (alertas.hayAlertas()) {
                        while (alertas.hayAlertas()) {
                            System.out.println("  > " + alertas.procesarAlerta().getMensaje());
                        }
                    } else {
                        System.out.println("  ✅ No hay alertas pendientes.");
                        System.out.print("¿Desea generar una alerta manual? (s/n): ");
                        String resp = scanner.nextLine();
                        if (resp.equalsIgnoreCase("s")) {
                            System.out.print("Ingrese mensaje de alerta: ");
                            String msg = scanner.nextLine();
                            alertas.agregarAlerta(msg);
                            System.out.println("  ✅ Alerta agregada.");
                        }
                    }
                    System.out.println("--------------------------");
                    break;

                case "7": // Ver nodos activos
                    System.out.println("\n🟢 --- Nodos Activos ---");
                    lista.imprimir();
                    System.out.println("----------------------");
                    break;

                case "8": // Salir
                    System.out.println("\n👋 Cerrando sistema...");
                    if (consumoIniciado && simuladorConsumo != null) simuladorConsumo.detener();
                    if (fallosIniciados && simuladorFallos != null) simuladorFallos.detener();
                    scanner.close();
                    return;

                default:
                    System.out.println("\n❌ Opción inválida. Por favor, seleccione una opción del menú.");
            }
            System.out.println(); // Añadir una línea en blanco para mejor legibilidad entre iteraciones
        }
    }

    // ---------- MAIN ----------

    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.agregarNodo("A");
        grafo.agregarNodo("B");
        grafo.agregarNodo("C");
        grafo.agregarArista("A", "B", 4);
        grafo.agregarArista("B", "A", -3);
        grafo.agregarArista("B", "C", 3);
        grafo.agregarArista("A", "C", 10);

        iniciarMenu(grafo);
    }
}