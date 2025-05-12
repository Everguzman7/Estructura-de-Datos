import java.util.*;

public class Grafo {
    private final Map<String, Map<String, Integer>> adyacencias;

    public Grafo() {
        this.adyacencias = new HashMap<>();
    }

    public void agregarNodo(String nodo) {
        adyacencias.putIfAbsent(nodo, new HashMap<>());
    }

    public void agregarArista(String origen, String destino, int peso) {
        adyacencias.putIfAbsent(origen, new HashMap<>());
        adyacencias.get(origen).put(destino, peso);
    }

    public List<String> getNodos() {
        return new ArrayList<>(adyacencias.keySet());
    }

    public Map<String, Integer> getAdyacentes(String nodo) {
        return adyacencias.getOrDefault(nodo, new HashMap<>());
    }

    public int[][] toMatrix() {
        int n = adyacencias.size();
        int[][] matriz = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = Integer.MAX_VALUE; // Inicializar con infinito
            }
        }

        List<String> nodos = new ArrayList<>(adyacencias.keySet());
        for (int i = 0; i < nodos.size(); i++) {
            String origen = nodos.get(i);
            for (Map.Entry<String, Integer> entrada : adyacencias.get(origen).entrySet()) {
                String destino = entrada.getKey();
                int peso = entrada.getValue();
                int j = nodos.indexOf(destino);
                matriz[i][j] = peso;
            }
        }
        return matriz;
    }

    public int getNodoIndex(String nodo) {
        List<String> nodos = new ArrayList<>(adyacencias.keySet());
        return nodos.indexOf(nodo);
    }

    public String getNodoName(int index) {
        List<String> nodos = new ArrayList<>(adyacencias.keySet());
        return nodos.get(index);
    }
}