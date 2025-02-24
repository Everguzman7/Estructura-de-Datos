
import java.util.Scanner;
 
 
public class EjercicioO1 {
    public static int obtenerPrimerElemento(int[] arr) {
        return arr[0];
    }
 
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el tamaño del array: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
 
        System.out.println("Ingrese los elementos del array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("El primer elemento del array es: " + obtenerPrimerElemento(arr));
        scanner.close();
    }
}


    
