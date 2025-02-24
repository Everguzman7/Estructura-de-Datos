package ejercicio4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContadordePalabras {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese una oración:");
        String oracion = sc.nextLine();
        oracion = oracion.replaceAll("[^a-zA-ZaeiouAEIOU\\s]", "");

        String[] palabras = oracion.split("\\s+");

        Map<String, Integer> contadorPalabras = new HashMap<>();

        for (String palabra : palabras) {
            if (contadorPalabras.containsKey(palabra)) {
                contadorPalabras.put(palabra, contadorPalabras.get(palabra) + 1);
            } else {
                contadorPalabras.put(palabra, 1);
            }
        }

        System.out.println("Conteo de palabras:");
        for (Map.Entry<String, Integer> entry : contadorPalabras.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        sc.close();
    }
}
