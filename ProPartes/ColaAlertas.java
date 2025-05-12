import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ColaAlertas {
    private final ConcurrentLinkedQueue<String> alertas; // Cola concurrente para alertas
    private final int limiteMaximo; // Límite máximo de alertas en la cola

    // Constructor
    public ColaAlertas(int limiteMaximo) {
        this.alertas = new ConcurrentLinkedQueue<>();
        this.limiteMaximo = limiteMaximo;
    }

    // Método para agregar una alerta a la cola
    public boolean agregarAlerta(String mensaje) {
        if (alertas.size() < limiteMaximo) {
            alertas.add(mensaje);
            System.out.println("⚠️ Alerta agregada: " + mensaje);
            return true;
        } else {
            System.out.println("⚠️ No se puede agregar la alerta. Límite máximo alcanzado.");
            return false;
        }
    }

    // Método para verificar si hay alertas pendientes
    public boolean hayAlertas() {
        return !alertas.isEmpty();
    }

    // Método para procesar la alerta más antigua
    public String procesarAlerta() {
        if (alertas.isEmpty()) {
            System.out.println("⚠️ No hay alertas para procesar.");
            return null;
        }
        String alertaProcesada = alertas.poll();
        System.out.println("✅ Alerta procesada: " + alertaProcesada);
        return alertaProcesada;
    }

    // Método para ver todas las alertas pendientes sin procesarlas
    public List<String> verAlertas() {
        return new ArrayList<>(alertas);
    }
}