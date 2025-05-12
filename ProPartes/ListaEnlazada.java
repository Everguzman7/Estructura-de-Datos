class ListaEnlazada {
    private static class Nodo {
        String nombre;
        Nodo siguiente;

        Nodo(String nombre) {
            this.nombre = nombre;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;

    public void agregar(String nombre) {
        Nodo nuevo = new Nodo(nombre);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    public void imprimir() {
        Nodo temp = cabeza;
        while (temp != null) {
            System.out.println("Nodo: " + temp.nombre);
            temp = temp.siguiente;
        }
    }
}