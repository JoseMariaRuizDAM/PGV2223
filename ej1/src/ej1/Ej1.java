package ej1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author dam2
 */
public class Ej1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        while(sc.hasNextInt()){ //mientras tenga un numero entero seguira recorriendo el while
            numeros.add(sc.nextInt());
        }
        
        Collections.sort(numeros); //ordenar la lista
        System.out.println(numeros);
        /*
        int numero = Integer.parseInt(args[0]);
        boolean aux = false;
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in); 
        System.out.println("Introduce un numero");
        do{
            
            numero = sc.nextInt();
            numeros.add(numero);
            aux = sc.hasNextInt();
        }while(aux == true);
        
        Collections.sort(numeros);
        for(Integer i : numeros){
            System.out.print(i + ";");
        }*/
//introducir en una lista los numeros
    }
    
}
