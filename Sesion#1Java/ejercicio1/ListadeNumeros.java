import java.util.Scanner;


public class ListadeNumeros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int[] numeros = new int[5];

        for (int i = 0; i < 5; i++){
            System.out.print("ingrese los numeros" + (i + 1) + " : ");
            numeros [i] = scanner.nextInt();
        
        }  

        int mayor = numeros[0];
        int menor = numeros[0];

        for (int i = 1; i < numeros.length; i++){
            if (numeros[i] > mayor){
                mayor = numeros[i];
            }
            if (numeros[i] < menor){
                menor = numeros[i];
            }
        }

        System.out.println("El numero mayor es: " + mayor);
        System.out.println("El numero menor es: " + menor);

        scanner.close();

    }

    
}