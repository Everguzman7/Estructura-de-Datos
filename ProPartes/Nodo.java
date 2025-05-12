// Clase que representa un nodo (subestación) en la red eléctrica
public class Nodo {
    private String nombre; // Nombre o identificador del nodo
    private double consumo; // Consumo de energía del nodo

    // Constructor de la clase Nodo
    public Nodo(String nombre) {
        this.nombre = nombre;
        this.consumo = 0; // Inicializa el consumo en 0
    }

    public String getNombre() {
        return nombre;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }
}