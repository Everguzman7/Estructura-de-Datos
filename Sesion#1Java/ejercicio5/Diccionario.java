package ejercicio5;

import java.util.Scanner;

public class Diccionario extends Ciudad {
    Diccionario(String nombre, int telefono) {
        super(nombre, telefono);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ciudad[] ciudades = new Ciudad[5];

        ciudades[0] = new Ciudad("bogota", 5);
        ciudades[1] = new Ciudad("medellin", 34);
        ciudades[2] = new Ciudad("ibague", 29);
        ciudades[3] = new Ciudad("jamundi", 40);
        ciudades[4] = new Ciudad("palmira", 25);

        System.out.println("Digite el nombre de la ciudad:");
        String nombre = sc.nextLine();

        String msg = "ciudad no encontrada";
        for (int i = 0; i < 5; i++) {
            if (ciudades[i].nombre.equals(nombre)) {
                System.out.println("La temperatura de " + nombre + " es: " + ciudades[i].temperatura + "°C o " + (ciudades[i].temperatura * 1.8 + 32) + "°F");
                msg = "";
            }
        }

        System.out.println(msg);
        sc.close();
    }
    
}
