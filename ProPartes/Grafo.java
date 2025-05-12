import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Clase que representa el grafo (red eléctrica)
public class Grafo {
    private Map<String, Nodo> nodos = new HashMap<>(); // Mapa para almacenar los nodos, la clave es el nombre del nodo
    private Map<String, Map<String, Double>> adyacencias = new HashMap<>();
    // Mapa para almacenar las conexiones (aristas) entre los nodos
    // La clave del primer mapa es el nodo de origen, y el valor es otro mapa
    // donde la clave es el nodo de destino y el valor es el peso de la arista (pérdidas)

    // Método para agregar un nodo al grafo
    public void agregarNodo(String nombre) {
        nodos.put(nombre, new Nodo(nombre)); // Crea un nuevo nodo y lo añade al mapa de nodos
        adyacencias.put(nombre, new HashMap<>()); // Inicializa la lista de adyacencia para este nodo
    }

    // Método para agregar una arista (conexión) entre dos nodos
    public void agregarArista(String origen, String destino, double peso) {
        // Verifica si los nodos de origen y destino existen
        if (!nodos.containsKey(origen)) {
            System.out.println("Error: El nodo de origen '" + origen + "' no existe en el grafo.");
            return;
        }
        if (!nodos.containsKey(destino)) {
            System.out.println("Error: El nodo de destino '" + destino + "' no existe en el grafo.");
            return;
        }
        adyacencias.get(origen).put(destino, peso); // Agrega la arista al mapa de adyacencias
    }

    // Método para desconectar dos nodos
    public void desconectar(String origen, String destino) {
        if (!nodos.containsKey(origen)) {
            System.out.println("Error: El nodo de origen '" + origen + "' no existe en el grafo.");
            return;
        }
        if (adyacencias.containsKey(origen)) {
            adyacencias.get(origen).remove(destino);
        }
    }

    // Método para obtener los vecinos de un nodo
    public Map<String, Double> getVecinos(String nodo) {
        return adyacencias.get(nodo); // Retorna el mapa de vecinos del nodo dado
    }

    // Método para obtener todos los nodos del grafo
    public Set<String> getNodos() {
        return nodos.keySet(); // Retorna el conjunto de nombres de los nodos
    }

    // Método para obtener un nodo por su nombre
    public Nodo getNodo(String nombre) {
        return nodos.get(nombre);
    }

    public Map<String, Map<String, Double>> getAdyacencias() {
        return adyacencias;
    }
}