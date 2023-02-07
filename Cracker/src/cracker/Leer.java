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
        
        String texto = "";
        try{
            Scanner entrada = new Scanner(socket.getInputStream());
            while(entrada.hasNext()){
                texto = entrada.nextLine();
                if(texto.equals(null)){
                    mensajes.add("cierre");
                }
                //si texto null, enviar info a escribir y cerrar socket (conexion)
                mensajes.add(texto);
            }

        }catch(IOException ex){
            
        }
         
        
    }
    
}
