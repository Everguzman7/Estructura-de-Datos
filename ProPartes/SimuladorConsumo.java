import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class SimuladorConsumo {
    private final Grafo grafo;
    private final Timer temporizador;
    private boolean ejecutando;

    public SimuladorConsumo(Grafo grafo) {
        this.grafo = grafo;
        this.temporizador = new Timer();
        this.ejecutando = true;
    }

    public void iniciar() {
        temporizador.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!ejecutando) {
                    temporizador.cancel();
                    return;
                }

                Random random = new Random();
                int consumo = random.nextInt(100); // Simular consumo aleatorio
                System.out.println("Consumo generado: " + consumo);
                System.out.println("Nodos en el grafo: " + grafo.getNodos());
                for (String nodo : grafo.getNodos()) {
                    double cambio = random.nextDouble() * 20 - 10; // Generar o consumir entre -10 y +10
                    System.out.println("Nodo " + nodo + " cambia su consumo en: " + String.format("%.2f", cambio));
                }
            }
        }, 0, 2000); // Ejecutar cada 2 segundos
    }

    public void detener() {
        ejecutando = false;
    }

    public boolean estaEjecutando() {
        return ejecutando;
    }
}