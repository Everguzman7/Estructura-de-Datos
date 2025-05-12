class SimuladorFallos extends Thread {
    private Grafo grafo;
    private ColaAlertas alertas;
    private boolean ejecutando = true;

    public SimuladorFallos(Grafo grafo, ColaAlertas alertas) {
        this.grafo = grafo;
        this.alertas = alertas;
    }

    public void detener() {
        ejecutando = false;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (ejecutando) {
            try {
                Thread.sleep(2000); // Simular fallos cada 2 segundos
                String nodo = grafo.getNodos().get(random.nextInt(grafo.getNodos().size()));
                alertas.agregarAlerta("Fallo detectado en el nodo: " + nodo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}