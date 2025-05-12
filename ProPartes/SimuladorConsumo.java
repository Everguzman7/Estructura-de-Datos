import java.util.Random;

class SimuladorConsumo extends Thread {
    private final Grafo grafo;
    private boolean ejecutando = false;

    public SimuladorConsumo(Grafo grafo) {
        this.grafo = grafo;
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
            for (String nodo : grafo.getNodos()) {
                int consumo = random.nextInt(100);
                System.out.println("[SimuladorConsumo] Nodo: " + nodo + ", Consumo: " + consumo);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("[SimuladorConsumo] Simulación detenida.");
    }
}