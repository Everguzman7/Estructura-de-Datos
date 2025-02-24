package ejercicio2;

import java.util.Scanner;

public class Diccionario extends Persona {
    public Diccionario(String nombre, int numero) {
        super(nombre, numero);
    }

    public static void main(String[] args) {
        Diccionario[] personas = new Diccionario[5];
        personas[0] = new Diccionario("Camilo", 224455);
        personas[1] = new Diccionario("Ever", 44332211);
        personas[2] = new Diccionario("Maria", 222222);
        personas[3] = new Diccionario("Jose", 666666);
        personas[4] = new Diccionario("cielo", 888888);

        System.out.println("Ingrese el nombre de la persona que desea buscar");
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.nextLine();
        String msg = "No se encontro el nombre";
        for (int i=0; i<5;i++){
            if (personas[i].nombre.equals(nombre)){
                System.out.println("El numero de " + nombre + " es: " + personas[i].numero);
             msg="";
            }
           
        }
        System.out.println(msg);
        scanner.close();
    
    }

}
