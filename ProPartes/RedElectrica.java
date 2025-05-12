import java.util.Scanner;

// Clase principal y única
public class RedElectrica {

    // Método para mostrar el menú de opciones
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

    // Método para iniciar el menú y la interacción con el usuario
    public static void iniciarMenu(Grafo grafo) {
        Scanner scanner = new Scanner(System.in); // Scanner para leer la entrada del usuario
        ColaAlertas alertas = new ColaAlertas(); // Cola para gestionar las alertas
        ListaEnlazada lista = new ListaEnlazada(); // Lista enlazada para almacenar los nodos activos
        SimuladorFallos simuladorFallos = null; // Simulador de fallos (inicialmente null)
        SimuladorConsumo simuladorConsumo = null; // Simulador de consumo (inicialmente null)
        boolean fallosIniciados = false;
        boolean consumoIniciado = false;

        for (String nombre : grafo.getNodos())
            lista.agregar(grafo.getNodo(nombre));

        // Bucle principal del menú
        while (true) {
            mostrarMenu(); // Muestra el menú
            String opcion = scanner.nextLine(); // Lee la opción del usuario

            switch (opcion) {
                case "1": // Calcular ruta de menor pérdida (Dijkstra)
                    System.out.print("Ingrese el nodo de inicio para calcular la ruta: ");
                    String inicioDijkstra = scanner.nextLine().toUpperCase();
                    if (grafo.getNodos().contains(inicioDijkstra)) {
                        Map<String, Double> distancias = Dijkstra.calcularRuta(grafo, inicioDijkstra);
                        System.out.println("\n🔍 Ruta mínima desde " + inicioDijkstra + ":");
                        distancias.forEach((k, v) -> System.out.println("  " + inicioDijkstra + " -> " + k + ": " + String.format("%.2f", v)));
                    } else {
                        System.out.println("❌ El nodo '" + inicioDijkstra + "' no existe en el grafo.");
                    }
                    break;

                case "2": // Análisis global de la red (Floyd-Warshall)
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
    // Método principal, punto de entrada del programa
    public static void main(String[] args) {
        Grafo grafo = new Grafo(); // Crea un nuevo grafo
        // Agrega algunos nodos y aristas de ejemplo
        grafo.agregarNodo("A");
        grafo.agregarNodo("B");
        grafo.agregarNodo("C");
        grafo.agregarArista("A", "B", 4);
        grafo.agregarArista("B", "A", -3);
        grafo.agregarArista("B", "C", 3);
        grafo.agregarArista("A", "C", 10);

        iniciarMenu(grafo); // Llama al método para iniciar el menú y la interacción con el usuario
    }
}