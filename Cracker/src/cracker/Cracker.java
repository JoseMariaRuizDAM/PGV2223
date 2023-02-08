package cracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dam2
 */
public class Cracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws FileNotFoundException, IOException{
        //Tengo que poner los usuarios aqui para que los lea aqui y haga el bucle desde aqui para la clase escribir
        LinkedList<String> mensajes = new LinkedList<String>();
        String Host = "localhost"; 
        int Puerto = 21;
        String user = "";
        String pass = "";
        try {
            Scanner sc = new Scanner(new File("usuarios.txt"));
            Scanner scContra = new Scanner(new File("claves.txt"));
            
             
            while(sc.hasNext()){
                user = sc.nextLine(); 
                    System.out.println("user main " + user);
               
                while (scContra.hasNext()) {
                    pass = scContra.nextLine();
                /*user = sc.nextLine(); 
                    System.out.println("user main " + user);*/
                Socket socket = new Socket(Host, Puerto);
                Leer leer = new Leer(socket, mensajes);
                leer.start();
                sleep(30);

                Escribir escribir = new Escribir(socket, mensajes, user, pass);
                escribir.start();
                }
            }
            System.out.println("termina main total");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
       // }
        /*
        Scanner sc = new Scanner(new File("usuarios.txt"));
        Scanner sc2 = new Scanner(new File("claves.txt"));
        String us;
        List<String> pass = new ArrayList<String>();
        
        while(sc2.hasNext()){
            pass.add(sc2.nextLine());
        }
         while(sc.hasNextLine()) {
             us = sc.nextLine();
             Leer usuario = new Leer(us, pass);
             usuario.start();
         }
         sc.close();9*/
    }
    
}