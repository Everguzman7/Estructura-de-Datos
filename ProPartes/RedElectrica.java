import java.util.Scanner;

public class RedElectrica {

    public static void mostrarMenu() {
        System.out.println("\n===== MENÚ DE GESTIÓN DE RED ELÉCTRICA =====");
        System.out.println("1. Calcular ruta de menor pérdida (Dijkstra)");
        System.out.println("2. Análisis global de la red (Floyd-Warshall)");
        System.out.println("3. Detectar pérdidas económicas negativas (Bellman-Ford)");
        System.out.println("4. Iniciar/Detener simulación de fallos");
        System.out.println("5. Iniciar/Detener simulación de consumo");
        System.out.println("6. Ver y procesar alertas");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void iniciarMenu(Grafo grafo) {
        ColaAlertas alertas = new ColaAlertas(10);
        SimuladorFallos simuladorFallos = new SimuladorFallos(grafo, alertas);
        SimuladorConsumo simuladorConsumo = new SimuladorConsumo(grafo);

        boolean salir = false;
        try (Scanner scanner = new Scanner(System.in)) {
            while (!salir) {
                mostrarMenu();
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                switch (opcion) {
                    case 1 -> {
                        System.out.println("\n===== DISTANCIAS ENTRE TODOS LOS NODOS =====");
                        Dijkstra dijkstra = new Dijkstra();
                        for (int origen = 0; origen < grafo.getNumeroNodos(); origen++) {
                            int[] distancias = dijkstra.calcularRutaMinima(grafo.toMatrix(), origen);
                            System.out.println("\nDesde el nodo " + grafo.getNodoName(origen) + ":");
                            System.out.printf("%-10s %-10s\n", "Nodo", "Distancia");
                            System.out.println("----------------------");
                            for (int destino = 0; destino < distancias.length; destino++) {
                                System.out.printf("%-10s %-10s\n", grafo.getNodoName(destino), (distancias[destino] == Integer.MAX_VALUE ? "INF" : distancias[destino]));
                            }
                        }
                        System.out.println("===============================================\n");
                    }
                    case 2 -> {
                        System.out.println("\n===== ANÁLISIS GLOBAL DE LA RED (FLOYD-WARSHALL) =====");
                        FloydWarshall floydWarshall = new FloydWarshall();
                        int[][] distancias = floydWarshall.calcularRutasMinimas(grafo.toMatrix());
                        System.out.println("\nMatriz de distancias mínimas:");
                        for (int[] fila : distancias) {
                            for (int valor : fila) {
                                System.out.print((valor == Integer.MAX_VALUE ? "INF" : valor) + " ");
                            }
                            System.out.println();
                        }
                        System.out.println("===============================================\n");
                    }
                    case 3 -> {
                        System.out.println("\n===== DETECCIÓN DE PÉRDIDAS ECONÓMICAS NEGATIVAS (BELLMAN-FORD) =====");
                        System.out.print("Ingrese el nodo de origen: ");
                        String origen = scanner.nextLine();
                        BellmanFord bellmanFord = new BellmanFord();
                        boolean tieneCiclos = bellmanFord.detectarCiclosNegativos(grafo.toMatrix(), grafo.getNodoIndex(origen));
                        if (tieneCiclos) {
                            System.out.println("\nSe detectaron ciclos negativos en la red.");
                        } else {
                            System.out.println("\nNo se detectaron ciclos negativos.");
                        }
                        System.out.println("===============================================\n");
                    }
                    case 4 -> {
                        if (simuladorFallos.estaEjecutando()) {
                            simuladorFallos.detener();
                            System.out.println("Simulación de fallos detenida.");
                        } else {
                            simuladorFallos.iniciar();
                            System.out.println("Simulación de fallos iniciada.");
                        }
                    }
                    case 5 -> {
                        if (simuladorConsumo.estaEjecutando()) {
                            simuladorConsumo.detener();
                            System.out.println("Simulación de consumo detenida.");
                        } else {
                            simuladorConsumo.iniciar();
                            System.out.println("Simulación de consumo iniciada.");
                        }
                    }
                    case 6 -> {
                        System.out.println("Procesando alertas...");
                        // Generar alertas de prueba
                        alertas.agregarAlerta("Alerta: Sobrecarga detectada en el nodo A.");
                        alertas.agregarAlerta("Alerta: Falla en la conexión entre los nodos B y C.");
                        alertas.agregarAlerta("Alerta: Consumo excesivo en el nodo C.");

                        // Procesar y mostrar las alertas
                        while (alertas.hayAlertas()) {
                            String alertaProcesada = alertas.procesarAlerta();
                            System.out.println(alertaProcesada);
                        }
                        System.out.println("No hay más alertas pendientes.");
                    }
                    case 7 -> System.out.println("Opción no válida.");
                    case 8 -> salir = true;
                    case 9 -> {
                        System.out.println("Nodos activos en la red:");
                        for (int i = 0; i < grafo.getNumeroNodos(); i++) {
                            System.out.println("Nodo " + grafo.getNodoName(i) + " está activo.");
                        }

                        // Generar más nodos y conexiones para ampliar la matriz
                        grafo.agregarNodo("D");
                        grafo.agregarNodo("E");
                        grafo.agregarNodo("F");
                        grafo.agregarArista("D", "E", 6);
                        grafo.agregarArista("E", "F", 7);
                        grafo.agregarArista("F", "D", 8);
                        grafo.agregarArista("A", "D", 9);
                        grafo.agregarArista("B", "E", 10);
                        grafo.agregarArista("C", "F", 11);

                        System.out.println("Se han agregado nuevos nodos y conexiones a la red.");
                    }
                    default -> System.out.println("Opción no válida.");
                }
            }
        }
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.agregarNodo("A");
        grafo.agregarNodo("B");
        grafo.agregarNodo("C");
        grafo.agregarArista("A", "B", 4);
        grafo.agregarArista("B", "C", 3);
        grafo.agregarArista("A", "C", 5);
        grafo.agregarArista("C", "A", 2);

        grafo.agregarNodo("D");
        grafo.agregarNodo("E");
        grafo.agregarNodo("F");
        grafo.agregarNodo("G");
        grafo.agregarNodo("H");
        grafo.agregarNodo("I");

        grafo.agregarArista("A", "D", 6);
        grafo.agregarArista("D", "E", 7);
        grafo.agregarArista("E", "F", 8);
        grafo.agregarArista("F", "G", 9);
        grafo.agregarArista("G", "H", 10);
        grafo.agregarArista("H", "I", 11);
        grafo.agregarArista("I", "A", 12);
        grafo.agregarArista("B", "F", 5);
        grafo.agregarArista("C", "G", 4);

        iniciarMenu(grafo);
    }
}