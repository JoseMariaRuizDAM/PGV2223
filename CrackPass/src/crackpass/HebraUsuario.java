package crackpass;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

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

            //scPasswords = new Scanner(new File("/tmp/claves.txt"));
            scPasswords = new Scanner(new File("claves.txt"));
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
                sleep(1000);
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