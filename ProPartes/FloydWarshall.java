// Implementación del algoritmo de Floyd-Warshall para análisis global de la red energética
public class FloydWarshall {
    public int[][] calcularRutasMinimas(int[][] grafo) {
        int n = grafo.length;
        int[][] distancias = new int[n][n];

        // Inicializar distancias
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distancias[i][j] = 0;
                } else if (grafo[i][j] != 0) {
                    distancias[i][j] = grafo[i][j];
                } else {
                    distancias[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancias[i][k] != Integer.MAX_VALUE && distancias[k][j] != Integer.MAX_VALUE && distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                    }
                }
            }
        }
        return distancias;
    }
}