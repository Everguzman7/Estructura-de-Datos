// Implementación del algoritmo de Dijkstra para calcular la ruta de menor pérdida de energía
public class Dijkstra {
    public int[] calcularRutaMinima(int[][] grafo, int origen) {
        int n = grafo.length;
        int[] distancias = new int[n];
        boolean[] visitados = new boolean[n];

        // Inicializar distancias
        for (int i = 0; i < n; i++) {
            distancias[i] = Integer.MAX_VALUE;
            visitados[i] = false;
        }
        distancias[origen] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = seleccionarNodoMinimo(distancias, visitados);
            visitados[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visitados[v] && grafo[u][v] != 0 && distancias[u] != Integer.MAX_VALUE && distancias[u] + grafo[u][v] < distancias[v]) {
                    distancias[v] = distancias[u] + grafo[u][v];
                }
            }
        }
        return distancias;
    }

    private int seleccionarNodoMinimo(int[] distancias, boolean[] visitados) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int i = 0; i < distancias.length; i++) {
            if (!visitados[i] && distancias[i] < min) {
                min = distancias[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}