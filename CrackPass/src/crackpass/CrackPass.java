package crackpass;

import crackpass.HebraUsuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author dam2
 */
public class CrackPass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        
        String ip = (args.length == 0) ? "localhost" : args[0];
        int puerto = (args.length <= 1) ? 21 : Integer.parseInt(args[1]);
        int numHebras = (args.length <= 2) ? 8 : Integer.parseInt(args[2]);
        
        LinkedList<HebraUsuario> listaUsuarios = new LinkedList<>();
        String usuario = "";
        Long tiempoIniciar = System.currentTimeMillis();
        HebraUsuario hu = null;
        
        Scanner scUsuarios;
        //scUsuarios = new Scanner(new File("/tmp/usuarios.txt"));
        scUsuarios = new Scanner(new File("usuarios.txt"));
        
        while(scUsuarios.hasNext()){
            
            while(listaUsuarios.size() < numHebras && scUsuarios.hasNext()){
                usuario = scUsuarios.nextLine();
                hu = new HebraUsuario(ip, puerto, usuario);
                hu.start();
                listaUsuarios.add(hu);
            }
            Iterator<HebraUsuario> listaIterator = listaUsuarios.iterator();  
            
            while(listaIterator.hasNext()){
                hu = listaIterator.next();
                if(hu.isAlive()){
                    //System.out.println("estoy viva" + hu.getUsuario());
                }
            }
            
        }
        
        for (HebraUsuario listaUsuario : listaUsuarios) {
            listaUsuario.join();
        }
        
         Long tiempoFinal = System.currentTimeMillis();
         Long tiempoTotal = (tiempoFinal - tiempoIniciar) / 1000;
         System.out.println("Tiempo total " + tiempoTotal + " seg");
    }
    
}
