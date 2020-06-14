package virus.util;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.image.Image;

public class Jugador {
    private String ID;
    private String nick;
    private int turno;
    private ArrayList<Carta> mano;
    private ArrayList<Carta> tablero;
    private Carta matTablero[][];
    
    public Jugador(){
        this.ID = "0";
        this.turno = 1;
        this.nick = " ";
        mano = new ArrayList();
        tablero = new ArrayList();
        matTablero = new Carta[5][3];
    }
    
    public Jugador(String ID, int turno, String nick){
        this.ID = ID;
        this.turno = turno;
        this.nick = nick;
        mano = new ArrayList();
        tablero = new ArrayList();
        matTablero = new Carta[5][3];
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Carta[][] getMatTablero() {
        return matTablero;
    }

    public void setMatTablero(Carta[][] matTablero) {
        this.matTablero = matTablero;
    }

    
    
    public String getId(){
        return this.ID;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
    
    public void addMano(Carta obj){
        mano.add(obj);
    }
    
    public void addTablero(Carta obj){
        tablero.add(obj);
    }
    
    public int getTurno(){
        return this.turno;
    }
    
    public ArrayList<Carta> getMano() {
        return mano;
    }

    public ArrayList<Carta> getTablero() {
        return tablero;
    }
    
    public void datosMatriz(String info){
        String[] filas = info.split(",");
        ArrayList<String> lista = new ArrayList();
        int pos = 0;
        for(String fila: filas){
            String[] segmentos = fila.split("-");
            lista.addAll(Arrays.asList(segmentos));
        }
        
        for(int a=0;a<5;a++){
            for(int b=0;b<3;b++){
                matTablero[a][b] = Carta.crearCarta(lista.get(pos));
                pos++;
            }
        }
        
        for(int a=0;a<5;a++){
            for(int b=0;b<3;b++){
                if(matTablero[a][b] != null){
                    System.out.print("("+matTablero[a][b].getTipo()+","+matTablero[a][b].getColor()+")");
                }else{
                    System.out.print("(0)");
                }
            }
            System.out.println();
        }
    }
    
    /*
    public String infoJugador(){
        String info = String.valueOf(ID) + "_";
        for(int a=0;a<mano.size();a++){
            if(mano.get(a).getClass().equals(Carta.class)){
                Carta aux = (Carta) mano.get(a);
                info += String.valueOf(aux.getTipo()) + ":";
                info += String.valueOf(aux.getColor());
                if(a+1 < mano.size()){
                    info += "-";
                }
            }else{
                Tratamiento aux = new Tratamiento();
                info += "4:";
                info += String.valueOf(aux.getTipo());
                if(a+1 < mano.size()){
                    info += "-";
                }
            }
        }
        
        info += " ";
        for(int a=0;a<tablero.size();a++){
            if(tablero.get(a).getClass().equals(Carta.class)){
                Carta aux = (Carta) tablero.get(a);
                info += String.valueOf(aux.getTipo()) + ":";
                info += String.valueOf(aux.getColor());
                if(a+1 < tablero.size()){
                    info += "-";
                }
            }else{
                Tratamiento aux = new Tratamiento();
                info += "4:";
                info += String.valueOf(aux.getTipo());
                if(a+1 < tablero.size()){
                    info += "-";
                }
            }
        
        }
        if(tablero.isEmpty()){
            info += "0";
        }
        return info;
    }
    */
    public void convertirCarta(){
        System.out.println("Tamaño de la mano " + mano.size());
        ArrayList<Carta> lista = new ArrayList();
        String dato;
        Image imagen;
        for (Object carta : tablero) {
            dato = (String) carta;
            char aux1 = dato.charAt(0);
            char aux2 = dato.charAt(2);
            imagen = AppContext.getInstance().getCarta(dato);
            lista.add(new Carta(Character.getNumericValue(aux1), Character.getNumericValue(aux2), imagen));
        }
        tablero.clear();
        tablero.addAll(lista);
        System.out.println(tablero.size());
        lista.clear();
        
        for (Object carta: mano){
            dato = (String) carta;
            char aux1 = dato.charAt(0);
            char aux2 = dato.charAt(2);
            imagen = AppContext.getInstance().getCarta(dato);
            lista.add(new Carta(Character.getNumericValue(aux1), Character.getNumericValue(aux2), imagen));
        }
        
        mano.clear();
        mano.addAll(lista);
        System.out.println(mano.size());
        lista.clear();
        
    }
    
    public String infJugador(){
        String info = "";
        info += ID+"_";
        for(Carta carta: mano){
            info += carta.getRepresentacion()+"-";
        }
        info = info.substring(0, info.length() - 1);
        for(int i = 0; i < 5; i++){
            for(int y = 0; y < 3; y++){
                info += matTablero[i][y].getRepresentacion()+",";
            }
        }
        info = info.substring(0, info.length() - 1);
        return info;
    }
    
    
    public void copiarMatrizOponente(Cuerpo matriz[][]){
        for(int a=0;a<5;a++){
            for(int b=0;b<3;b++){
                matTablero[a][b] = matriz[a][b].getCarta();
            }
        }
    }
    
    public void copiarMatrizJugador(Cuerpo matriz[][]){
        for(int a=0;a<5;a++){
            for(int b=0;b<3;b++){
                matTablero[b][a] = matriz[a][b].getCarta();
            }
        }
    }
    
    /*public void Prueba(){
        tablero.add("1:1");
        tablero.add("2:4");
        tablero.add("3:2");
        tablero.add("4:4");
        mano.add("4:1");
        mano.add("3:4");
        mano.add("1:5");
        convertirCarta();
        for (Object carta : tablero){
            if (carta.getClass().equals(Carta.class)){
                Carta aux = (Carta) carta;
                aux.imprimirInfo();
            }else{
                Tratamiento aux = (Tratamiento) carta;
                aux.imprimirInfo();
            }
        }
    }*/
}
