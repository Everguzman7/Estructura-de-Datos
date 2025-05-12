import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class SimuladorFallos {
    private final Grafo grafo;
    private final ColaAlertas alertas;
    private final Timer temporizador;
    private int iteraciones;

    public SimuladorFallos(Grafo grafo, ColaAlertas alertas) {
        this.grafo = grafo;
        this.alertas = alertas;
        this.temporizador = new Timer();
        this.iteraciones = 0;
    }

    public void iniciar() {
        temporizador.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (iteraciones >= 5) {
                    temporizador.cancel();
                    return;
                }

                Random random = new Random();
                String nodo = grafo.getNodos().stream()
                        .skip(random.nextInt(grafo.getNodos().size()))
                        .findFirst()
                        .orElse(null);

                if (nodo != null) {
                    alertas.agregarAlerta("Fallo detectado en el nodo: " + nodo);
                    System.out.println("Alerta generada: " + alertas);
                }

                iteraciones++;
            }
        }, 0, 2000); // Ejecutar cada 2 segundos
    }

    public void detener() {
        temporizador.cancel();
    }

    public boolean estaEjecutando() {
        return iteraciones < 5;
    }
}