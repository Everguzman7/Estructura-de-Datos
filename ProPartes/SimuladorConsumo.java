class SimuladorConsumo extends Thread {
    private Grafo grafo;
    private boolean ejecutando = true;

    public SimuladorConsumo(Grafo grafo) {
        this.grafo = grafo;
    }

    public void detener() {
        ejecutando = false;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (ejecutando) {
            try {
                Thread.sleep(2000); // Modificar consumo cada 2 segundos
                for (String nodo : grafo.getNodos()) {
                    double cambio = random.nextDouble() * 20 - 10; // Generar o consumir entre -10 y +10
                    System.out.println("Nodo " + nodo + " cambia su consumo en: " + String.format("%.2f", cambio));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}