/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ej5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Integer;

/**
 *
 * @author dam2
 * 
 * ToDo: Realizar un metodo que reciba 2 listas de numeros enteros,
 * ambas ordenadas y nos devuelva una lista con todos los numeros de 
 * ambas ordenadas
 */
public class Ej5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<Integer> lista1 = Arrays.asList(1,3,4,5,10,15,17,20);
        List<Integer> lista2 = Arrays.asList(2,3,7,9,11,15,19,21);
        List<Integer> mezcla = new ArrayList<>();
        int num;
        int num2;
        int j = 0;
        int i = 0;
        //Recorrera las dos listas hasta que se terminen
        while(i != lista1.size() && j != lista2.size()){
 
            num = lista1.get(i); //recoge el numero de la lista de la posicion i
            num2 = lista2.get(j); //recoge el numero de la lista de la posicion j
            
            //Compara el numero de la primera lista con el segundo si es menor o igual lo introduce en la nueva lista            
            if(num <= num2){
                mezcla.add(num);
                i++;
            }
            // Si no es menor el de la primera lista introduce el de la segunda lista en la nueva lista
            else{
                
                num2 = lista2.get(j);
                mezcla.add(num2);
                j++;
            }           
        }     
        
        for (Integer numeros : mezcla) {
            System.out.print(numeros + "-" );
        }
    }  
}
