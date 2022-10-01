/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficherolenguaje;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class FicheroLenguaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String nombreFichero = args[0];
        int numeroFichero = Integer.parseInt(args[1]);
        
        System.out.println("nombre del fichero" + nombreFichero);
        System.out.println("numeros "+ numeroFichero  );
        new Escribir(nombreFichero, numeroFichero).start();
        System.out.println("Ha terminado");
    }
}

    class Escribir extends Thread{
    
        String nombreFichero;
        int numeroLetras;
        
        public Escribir(String nombre, int numeroLetras){
            this.nombreFichero = nombre;
            this.numeroLetras = numeroLetras;
        }
        public void run(){
            File fichero = new File(nombreFichero);
            Random rnd = new Random();
        
            String letras = "abcdefghijklmnopqrstuvwxyz";
        
            if(fichero.exists()){
                for(int i = 0; i <= numeroLetras; i++){
                    try{    
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true));
                        int rndint = rnd.nextInt(letras.length());
                        char rndLetra = letras.charAt(rndint);
                        bw.write(rndLetra);
                        bw.close();   
                    }catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }
            else{
                
                try {
                    fichero.createNewFile();
                    if(fichero.exists()){
                    for(int i = 0; i <= numeroLetras; i++){
                        try{    
                            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true));
                            int rndint = rnd.nextInt(letras.length());
                            char rndLetra = letras.charAt(rndint);
                            bw.write(rndLetra);
                            bw.close();   
                        }catch(IOException ioe){
                            ioe.printStackTrace();
                        }
                    }
                
                        
                }else{
                    System.out.println("El fichero no se ha creado correctamente");
                }
                } catch (IOException ex) {
                    Logger.getLogger(Escribir.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }
    }

    

