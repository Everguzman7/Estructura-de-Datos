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

    // Agregar un nodo al final de la lista
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

    // Imprimir todos los nodos de la lista
    public void imprimir() {
        Nodo temp = cabeza;
        while (temp != null) {
            System.out.println("Nodo: " + temp.nombre);
            temp = temp.siguiente;
        }
    }

    // Eliminar un nodo por nombre
    public boolean eliminar(String nombre) {
        if (cabeza == null) return false;

        if (cabeza.nombre.equals(nombre)) {
            cabeza = cabeza.siguiente;
            return true;
        }

        Nodo temp = cabeza;
        while (temp.siguiente != null && !temp.siguiente.nombre.equals(nombre)) {
            temp = temp.siguiente;
        }

        if (temp.siguiente != null) {
            temp.siguiente = temp.siguiente.siguiente;
            return true;
        }

        return false;
    }

    // Verificar si la lista está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }
}