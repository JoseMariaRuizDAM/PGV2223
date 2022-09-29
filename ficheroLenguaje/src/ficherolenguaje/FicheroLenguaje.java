package ficherolenguaje;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author dam2
 */
public class FicheroLenguaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Introduce el nombre del fichero");
        String nombreFichero = args[0];
        File fichero = new File(nombreFichero);
        
        if(fichero.exists()){
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
                bw.close();   
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
            
            
        }
    }
    
}
