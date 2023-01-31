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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

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

    public void run() {
        String respuesta = "";

        while (!cierre) {
            try {

                while (mensajes.size() > 0) {
                    respuesta = mensajes.pop();
                    System.out.println(respuesta);
                }

                PrintWriter escribir = new PrintWriter(socket.getOutputStream());

                if (mensajes.size() <= 0) {
                    escribir.println("user dam2");
                    escribir.flush();
                    
                    System.out.println(mensajes);

                    Thread.sleep(5);
                    System.out.println("despues sleep " + id + " " + mensajes);
                    
                    mensaje = mensajes.pop();
                    if (mensaje.contains("331")) {
                        System.out.println("Hola22");
                        escribir.println("pass PR0GR4M4");
                        escribir.flush();

                    }

                    while (mensajes.isEmpty()) {
                        Thread.sleep(250);
                    }

                    mensaje = mensajes.pop();
                    System.out.println("prueba " + id + " "+ mensaje);
                    if (mensaje.contains("530")) {
                        
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
}
