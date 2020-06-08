package virus.socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Conexion {
    
    Socket cliente, socket;
    ServerSocket servidor;
    int puerto = 44440;
    String ip = "192.168.8.104";
    PrintStream salida;
    InputStreamReader respuesta;
    char msg[];
    
    public Conexion(){
        msg = new char[1000];
    }
    
    public void accionEnviar(String id, String data){
        try{
            //Creamos la conexion con el servidor
            cliente = new Socket(ip, puerto);
            //Para recibir respuesta del servidor
            respuesta = new InputStreamReader(cliente.getInputStream());
            //Para enviar mensaje al servido
            salida = new PrintStream(cliente.getOutputStream());
            //Se envia el mensaje al servido
            salida.println(id+data);
            //Se recibe la respuesta del servido
            respuesta.read(msg);
            System.out.println(String.copyValueOf(msg));
            cliente.close();
            recibirRespuesta();
        }catch(IOException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }finally{
            try {
                respuesta.close();
                salida.close();
            } catch (IOException ex) {
                System.out.println("Ha ocurrido un error: "+ex);
            }
        }
    }
    
    public void recibirRespuesta(){
        String mensaje = String.copyValueOf(msg);
        String clave = mensaje.substring(0, 1);
        mensaje = mensaje.substring(2, mensaje.length());
        System.out.println("Clave: "+clave+" Mensaje: "+mensaje);
        if(clave.equals("1")){
            String[] cont = mensaje.split(":");
            System.out.println(cont[0]+":"+cont[1]);
        }
    }
}
