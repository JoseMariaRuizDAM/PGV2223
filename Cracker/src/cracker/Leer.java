/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author dam2
 */
public class Leer extends Thread{
   
    Socket socket;
    LinkedList<String> mensajes;
    Leer(){
        
    }
    Leer(Socket socket,  LinkedList<String> mensajes){
        this.socket = socket;
        this.mensajes = mensajes;
    }
    
    public void run () {
        /*try {
  
           
            BufferedReader mensaje = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            LinkedList<String> mensajes2 = mensaje.lines();
            mensajes2.forEach(m->{
            System.out.println(m);
                mensajes.add(m);
            });
            
            for (String object : mensajes2) {
                
            }
            //Probando a escribir en el leer
            System.out.println("hoa");
            PrintWriter escribir = new PrintWriter(socket.getOutputStream(), true);
             escribir.print("USER dam2\n");
             //escribir.flush();
                escribir.print("PASS PR0GR4M");
                System.out.println("hola22");
                //fin
            for (Iterator<String> iterator = mensajes2.iterator(); iterator.hasNext();) {
                String next = iterator.next();
                System.out.println(next);
                mensajes.add(next);
            }
                
        } catch (IOException ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
        String texto = "";
        try{
            Scanner entrada = new Scanner(socket.getInputStream());

            while(entrada.hasNext()){
                texto = entrada.nextLine();
                mensajes.add(texto);
            }

        }catch(IOException ex){
            
        }
         
        
    }
    
}
