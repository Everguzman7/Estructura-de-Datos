import java.util.ArrayList;
import java.util.Scanner;

public class ListaCompras {
   private ArrayList<String> elementos;

    public ListaCompras() {
        this.elementos = new ArrayList<>();
    }

    // Método para agregar un elemento a la lista
    public void agregarElemento(String elemento) {
        this.elementos.add(elemento);
    }

    // Método para eliminar un elemento de la lista
    public void eliminarElemento(String elemento) {
        if (this.elementos.contains(elemento)) {
            this.elementos.remove(elemento);
            System.out.println("Elemento eliminado con éxito");
        } else {
            System.out.println("Elemento no encontrado en la lista");
        }
    }

    // Método para imprimir la lista de compras
    public void imprimirLista() {
        System.out.println("Lista de compras:");
        for (String elemento : this.elementos) {
            System.out.println(elemento);
        }
    }
}

