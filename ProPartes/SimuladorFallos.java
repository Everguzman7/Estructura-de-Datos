import java.util.Random;

class SimuladorFallos extends Thread {
    private final Grafo grafo;
    private final ColaAlertas alertas;
    private boolean ejecutando = false;

    public SimuladorFallos(Grafo grafo, ColaAlertas alertas) {
        this.grafo = grafo;
        this.alertas = alertas;
    }

    public void iniciar() {
        if (!ejecutando) {
            ejecutando = true;
            new Thread(this).start();
        }
    }

    public void detener() {
        ejecutando = false;
    }

    public boolean estaEjecutando() {
        return ejecutando;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (ejecutando) {
            String nodo = grafo.getNodos().stream()
                    .skip(random.nextInt(grafo.getNodos().size()))
                    .findFirst()
                    .orElse(null);
            if (nodo != null) {
                alertas.agregarAlerta("Fallo detectado en el nodo: " + nodo);
                System.out.println("[SimuladorFallos] Fallo detectado en el nodo: " + nodo);
            } else {
                System.out.println("[SimuladorFallos] No se pudo seleccionar un nodo.");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("[SimuladorFallos] Simulación detenida.");
    }
}