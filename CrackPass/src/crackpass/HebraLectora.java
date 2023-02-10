
package crackpass;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author dam2
 */
public class HebraLectora extends Thread{
    Socket socket;
    LinkedList<String> mensajes;
    
    public HebraLectora(){}
    
    public HebraLectora(Socket socket, LinkedList<String> mensajes){
        this.socket = socket;
        this.mensajes = mensajes;
    }
    
    @Override
    public void run(){
         String texto = "";
        try{
            Scanner entrada = new Scanner(socket.getInputStream());
            while(entrada.hasNext()){
                
                texto = entrada.nextLine();
                //System.out.println(texto);
                /*if(texto.equals(null)){
                    mensajes.add("cierre");
                }*/
                //si texto null, enviar info a escribir y cerrar socket (conexion)
                mensajes.add(texto);
            }

        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public Socket getSocket() {
        return socket;
    }

    public LinkedList<String> getMensajes() {
        return mensajes;
    }
    
    
}