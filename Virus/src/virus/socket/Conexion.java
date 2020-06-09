package virus.socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import virus.Controller.IngresoController;
import virus.util.AppContext;
import virus.util.Jugador;


public class Conexion {
    
    Socket cliente, socket;
    ServerSocket servidor;
    int puerto = 44440;
    String ip = "25.91.116.230";
    PrintStream salida;
    InputStreamReader respuesta;
    char msg[];
    
    public Conexion(){
        
    }
    
    public void accionEnviar(String id, String data){
        try{
            msg = new char[1000];
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
        IngresoController aux = (IngresoController) AppContext.getInstance().get("Ingreso");
        String mensaje = String.copyValueOf(msg);
        msg = null;
        String clave = mensaje.substring(0, 1);
        mensaje = mensaje.substring(2, mensaje.length());
        switch(clave){
            case "1":
                String[] cont = mensaje.split(":");
                aux.crearJugadorPrincipal(cont[0], cont[1]);
                break;
            case "2":
                aux.actualizarJugadores(mensaje);
                break;
            case "3":
                Jugador player = (Jugador) AppContext.getInstance().get("Jugador");
                ArrayList<Jugador> jugadores = new ArrayList<>();
                jugadores.add(player);
                String[] cont2 = mensaje.split("/");
                for(int i = 0; i < cont2.length; i++){
                    if(i < (cont2.length - 1)){
                        String[] datJug = cont2[i].split("_");
                        if(player.getId().equals(datJug[0])){
                            Jugador jug = new Jugador(datJug[0], datJug[1], datJug[2]);
                            String[] manaux = datJug[datJug.length - 1].split("_");
                            for(int k = 0; k < manaux.length; k++){
                                player.addMano(AppContext.getInstance().getCarta(manaux[k]));
                            }
                        }else{
                            String[] mano = datJug[datJug.length - 1].split("_");  
                            for(int k = 0; k < mano.length; k++){
                                player.addMano(AppContext.getInstance().getCarta(mano[k]));
                            }
                        }
                    }
                }
                AppContext.getInstance().set("Juagdores", jugadores);
                break;
            default:
                break;
        }
    }
}
