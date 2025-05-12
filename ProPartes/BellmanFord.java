// Implementación del algoritmo de Bellman-Ford para detección de ciclos negativos
public class BellmanFord {
    public boolean detectarCiclosNegativos(int[][] grafo, int origen) {
        int n = grafo.length;
        int[] distancias = new int[n];

        // Inicializar distancias
        for (int i = 0; i < n; i++) {
            distancias[i] = Integer.MAX_VALUE;
        }
        distancias[origen] = 0;

        // Relajación de aristas
        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (grafo[u][v] != 0 && distancias[u] != Integer.MAX_VALUE && distancias[u] + grafo[u][v] < distancias[v]) {
                        distancias[v] = distancias[u] + grafo[u][v];
                    }
                }
            }
        }

        // Comprobar ciclos negativos
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (grafo[u][v] != 0 && distancias[u] != Integer.MAX_VALUE && distancias[u] + grafo[u][v] < distancias[v]) {
                    return true; // Ciclo negativo detectado
                }
            }
        }
        return false;
    }
}