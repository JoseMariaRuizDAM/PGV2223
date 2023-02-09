/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crackeador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author dam2
 */
public class Crackeador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        String ip = (args.length == 0) ? "localhost" : args[0];
        int puerto = (args.length <= 1) ? 21 : Integer.parseInt(args[1]);
        int numHebras = (args.length <= 2) ? 8 : Integer.parseInt(args[2]);
        
        LinkedList<HebraUsuario> listaUsuarios = new LinkedList<>();
        String usuario = "";
        Long tiempoIniciar = System.currentTimeMillis();
        HebraUsuario hu = null;
        
        Scanner scUsuarios;
        scUsuarios = new Scanner(new File("/tmp/usuarios.txt"));
        
        while(scUsuarios.hasNext()){
            usuario = scUsuarios.nextLine();
            
            while(listaUsuarios.size() < numHebras && scUsuarios.hasNext()){
                hu = new HebraUsuario(ip, puerto, usuario);
                listaUsuarios.add(hu);
                usuario = scUsuarios.nextLine();
            }
            
                
            
        }
    }
    
}
