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
        System.out.println("7. Ver nodos activos");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void iniciarMenu(Grafo grafo) {
        ColaAlertas alertas = new ColaAlertas(10);
        ListaEnlazada lista = new ListaEnlazada();
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
                        System.out.print("Ingrese el nodo de origen: ");
                        String origen = scanner.nextLine();
                        Dijkstra dijkstra = new Dijkstra();
                        int[] distancias = dijkstra.calcularRutaMinima(grafo.toMatrix(), grafo.getNodoIndex(origen));
                        System.out.println("Distancias desde el nodo " + origen + ":");
                        for (int i = 0; i < distancias.length; i++) {
                            System.out.println("Nodo " + grafo.getNodoName(i) + ": " + distancias[i]);
                        }
                    }
                    case 2 -> {
                        FloydWarshall floydWarshall = new FloydWarshall();
                        int[][] distancias = floydWarshall.calcularRutasMinimas(grafo.toMatrix());
                        System.out.println("Matriz de distancias mínimas:");
                        for (int i = 0; i < distancias.length; i++) {
                            for (int j = 0; j < distancias[i].length; j++) {
                                System.out.print((distancias[i][j] == Integer.MAX_VALUE ? "INF" : distancias[i][j]) + " ");
                            }
                            System.out.println();
                        }
                    }
                    case 3 -> {
                        System.out.print("Ingrese el nodo de origen: ");
                        String origen = scanner.nextLine();
                        BellmanFord bellmanFord = new BellmanFord();
                        boolean tieneCiclos = bellmanFord.detectarCiclosNegativos(grafo.toMatrix(), grafo.getNodoIndex(origen));
                        if (tieneCiclos) {
                            System.out.println("Se detectaron ciclos negativos en la red.");
                        } else {
                            System.out.println("No se detectaron ciclos negativos.");
                        }
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
                        while (alertas.hayAlertas()) {
                            alertas.procesarAlerta();
                        }
                    }
                    case 7 -> lista.imprimir();
                    case 8 -> salir = true;
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

        iniciarMenu(grafo);
    }
}