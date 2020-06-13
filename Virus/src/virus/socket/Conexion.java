 package virus.socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javafx.scene.image.Image;
import virus.Controller.IngresoController;
import virus.Controller.JuegoController;
import virus.util.AppContext;
import virus.util.Jugador;


public class Conexion {
    
    Socket cliente, socket;
    ServerSocket servidor;
    int puerto = 44440;
    String ip = "192.168.8.104";
    PrintStream salida;
    InputStreamReader respuesta;
    char msg[];
    
    public Conexion(){
        
    }
    
    public void accionEnviar(String id, String data){
        try{
            msg = new char[1000];
            for(int i = 0; i < 1000; i++){
                msg[i] = '*';
            }
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
        String mensaje = "";
        for(int i = 0; i < 1000; i++){
            if(msg[i] != '*')
                mensaje += msg[i];
            else
                break;
        }
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
                        if(!player.getId().equals(datJug[0])){
                            int turn = 0;
                            try{
                                turn = Integer.parseInt(datJug[1]);
                            }catch(NumberFormatException ex){}
                            Jugador jug = new Jugador(datJug[0], turn, datJug[2]);
                            String[] manaux = datJug[datJug.length - 1].split("-");
                            for(int k = 0; k < manaux.length; k++){
                                jug.addMano(manaux[k]);
                            }
                        }else{
                            String[] mano = datJug[datJug.length - 1].split("-");  
                            
                            for(int k = 0; k < mano.length; k++){
                                System.out.println(mano[k]);
                                player.addMano(mano[k]);
                            }
                        }
                    }else{
                        ArrayList<String> mazo = new ArrayList<>();
                        String[] cartas = cont2[i].split("_");
                        for(int a = 0; a < cartas.length; a++){
                            mazo.add(cartas[a]);
                        }
                        AppContext.getInstance().set("Mazo", mazo);
                    }
                }
                AppContext.getInstance().set("Turno", 1);
                AppContext.getInstance().set("Jugadores", jugadores);
                AppContext.getInstance().set("Iniciar", true);
                break;
            case "4":
                String[] message = mensaje.split("/");
                int turno = 0;
                try{
                    turno = Integer.parseInt(message[0]);
                }catch(NumberFormatException ex){
                    
                }
                AppContext.getInstance().set("Turno", turno);
                if(!message[1].equals("0")){
                    datosJugador(message[2].split(":"));
                    crearMazo(message[3].split("-"));
                    crearDescartes(message[4].split("-"));
                    usoTratamienro(message[2].split("-"));
                }
                    /*
                    String[] datosJuego = message[2].split("/");
                    for(int i = 0; i < datosJuego.length; i++){
                        if(i < datosJuego.length - 4){
                            datosJugador(datosJuego[i].split("_"));
                        }else if(i == datosJuego.length - 4){
                            String[] cartas = datosJuego[i].split("-");
                            ArrayList<String> mazo = new ArrayList<>();
                            for(int a = 0; a < cartas.length; a++){
                                mazo.add(cartas[a]);
                            }
                            AppContext.getInstance().set("Mazo", mazo);
                        }else if(i == datosJuego.length - 3){
                            String[] cartas = datosJuego[i].split("-");
                            ArrayList<String> descartes = new ArrayList<>();
                            for(int a = 0; a < cartas.length; a++){
                                descartes.add(cartas[a]);
                            }
                            AppContext.getInstance().set("Descartes", descartes);
                        }else{
                            break;
                        }
                    }
                    String especial = datosJuego[datosJuego.length-2];
                    JuegoController juego = (JuegoController) AppContext.getInstance().get("Juego");
                    if(!especial.equals("0")){
                        juego.informarCartaEspecial(especial, datosJuego[datosJuego.length-1]);
                    }
                    juego.actualizarJuego();
                }else{
                    String id = message[2];
                }*/
                break;
            default:
                break;
        }
    }
    
    private void datosJugador(String[] data){
        ArrayList<Jugador> jugadores = (ArrayList<Jugador>) AppContext.getInstance().get("Jugadores");
        String[] jugador, cartas;
        for(String player: data){
            jugador = player.split("_");
            for(Jugador jug: jugadores){
                if(jug.getId().equals(jugador[0])){
                    cartas = jugador[1].split("-");
                    for(String carta: cartas){
                        jug.addMano(carta);
                    }
                    //jug.infodatosMatriz(jugador[2]);
                }
            }
        }
        /*
        for(int i = 0; i < jugadores.size(); i++){
            jugador = data[0].split("_");
            if(jugadores.get(i).getId().equals(jugador[0])){
                cartas = data[1].split("-");
                jugadores.get(i).getMano().clear();
                for(int card = 0; card < cartas.length; card++){
                    jugadores.get(i).addMano(cartas[card]);
                }
                if(!data[2].equals("0")){
                    jugadores.get(i).getTablero().clear();
                    cartas = data[2].split("-");
                    for(int card = 0; card < cartas.length; card++){
                        jugadores.get(i).addTablero(cartas[card]);
                    }
                }
            }
        }*/
    }
    
    private void crearMazo(String[] mazo){
        ArrayList<Object> Mazo = new ArrayList<>();
        for(String carta: mazo){
            char prima = carta.charAt(0);
            if(prima != '4'){
                
            }
        }
        AppContext.getInstance().set("Mazo", Mazo);
    }
    
    private void crearDescartes(String[] descartes){
        for(String carta: descartes){
            
        }
    }
    
    private void usoTratamienro(String[] tratamiento){
        if(!tratamiento[0].equals("0")){
            JuegoController juego = (JuegoController) AppContext.getInstance().get("Juego");
            juego.informarCartaEspecial(tratamiento[0], tratamiento[1], tratamiento[2]);
        }
    }
}
