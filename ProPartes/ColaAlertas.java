class ColaAlertas {
    private Queue<String> alertas = new LinkedList<>();

    public void agregarAlerta(String mensaje) {
        alertas.add(mensaje);
    }

    public boolean hayAlertas() {
        return !alertas.isEmpty();
    }

    public String procesarAlerta() {
        return alertas.poll();
    }
}