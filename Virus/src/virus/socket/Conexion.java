package virus.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    
    Socket cliente, socket;
    ServerSocket servidor;
    int puerto = 44440;
    String ip = "192.168.8.104";
    PrintStream salida;
    InputStreamReader respuesta;
    InputStreamReader dataInputStream;
    DataOutputStream dataOutputStream;
    char msg[];
    
    public Conexion(){
        msg = new char[500];
    }
    
    private String Codificar(String data){
        int iterj = 0, clave[] = {3, 5, 2};
        char cadena[] = data.toCharArray();
        char caracter;
        for(int i = 0; i < data.length(); i++){
            caracter = cadena[i];
            if(caracter >= 65 && caracter <= 90){
                caracter += clave[iterj];
                if(caracter > 90)
                    caracter = (char) (caracter + 64 - 90);
            }else if(caracter == 32){
                caracter = (char)126;
            }
            cadena[i] = caracter;
            if(iterj < 2)
                iterj++;
            else
                iterj = 0;
        }
        return String.copyValueOf(cadena);
    }
    
    private String Decodificar(String data){
        int iterj = 0, clave[] = {3, 5, 2};
        char cadena[] = data.toCharArray();
        char caracter;
        for(int i = 0; i < data.length(); i++){
            caracter = cadena[i];
            if(caracter >= 65 && caracter <= 90){
                caracter -= clave[iterj];
                if(caracter < 65)
                    caracter = (char) (caracter - 64 + 90);
            }else if(caracter == 126){
                caracter = (char)32;
            }
            cadena[i] = caracter;
            if(iterj < 2)
                iterj++;
            else
                iterj = 0;
        }
        return String.copyValueOf(cadena);
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
        }catch(IOException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }finally{
            try {
                cliente.close();
                respuesta.close();
                salida.close();
            } catch (IOException ex) {
                System.out.println("Ha ocurrido un error: "+ex);
            }
        }
    }
    
    public void accionRecibir(){
        try {
            servidor = new ServerSocket(5000);
            System.out.println("Servidor Iniciado");
            boolean isServidor = true;
            
            while(!servidor.isClosed()){
                
                socket = servidor.accept();//para qeudarse a la espera a ver si hay alguna peticion
                
                System.out.println("****************************Cliente Conectado****************************");
                
                dataInputStream = new InputStreamReader(socket.getInputStream()); //Del cliente al servidor 
                
                System.out.println("Aqui");
                
                dataInputStream.read(msg);
                
                String mensaje = String.copyValueOf(msg);
                
                System.out.println("Aqui 2");
                
                dataOutputStream = new DataOutputStream(socket.getOutputStream()); //Del Servidor al Cliente
                
                dataOutputStream.writeUTF("Hola Mundo, desde este server juaz xd");//enviamos un mensaje al cliente
                
                //con estos dos nos comunicamos entre cliente y el servidor
                
                
                
                System.out.println(mensaje);//ostramos el mensaje del cliente
                
                
                //cerramos el cliente solamente
                System.out.println("****************************Cliente Desconecetado****************************");
                
                //cerramos el servidor de un solo para que no siga ahi andando... si se desea que sea ciclico nada mas quitar estads dos lineas de abajo...
                
                
            }
        } catch (IOException ex) {
            System.out.println("Error---->" + ex);
        }
        finally{
            try {
                dataInputStream.close();
                dataOutputStream.close();
                servidor.close();
            } catch (IOException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
