package virus.util;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Jugador {
    private String ID;
    private String nick;
    private int turno;
    private ArrayList<Object> mano;
    private ArrayList<Object> tablero;//1:1
    
    public Jugador(){
        this.ID = "0";
        this.turno = 1;
        this.nick = " ";
        mano = new ArrayList();
        tablero = new ArrayList();
    }
    
    public Jugador(String ID, int turno, String nick){
        this.ID = ID;
        this.turno = turno;
        this.nick = nick;
        mano = new ArrayList();
        tablero = new ArrayList();
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
    
    public void addMano(Object obj){
        mano.add(obj);
    }
    
    public void addTablero(Object obj){
        tablero.add(obj);
    }
    
    public int getTurno(){
        return this.turno;
    }
    
    public ArrayList<Object> getMano() {
        return mano;
    }

    public ArrayList<Object> getTablero() {
        return tablero;
    }
    
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
    
    public void convertirCarta(){
        System.out.println("Tamano: "+mano.size());
        ArrayList<Object> lista = new ArrayList();
        String dato = "";
        Image imagen;
        for (Object carta : tablero) {
            dato = (String) carta;
            char aux1 = dato.charAt(0);
            char aux2 = dato.charAt(2);
            imagen = AppContext.getInstance().getCarta(dato);
            if(aux1 != 4){
                lista.add(new Carta(Character.getNumericValue(aux1), Character.getNumericValue(aux2), imagen));
            }else{
                lista.add(new Tratamiento(Character.getNumericValue(aux2), imagen));
            }
        }
        
        tablero.clear();
        tablero.addAll(lista);
        lista.clear();
        
        for (Object carta: mano){
            dato = (String) carta;
            char aux1 = dato.charAt(0);
            char aux2 = dato.charAt(2);
            imagen = AppContext.getInstance().getCarta(dato);
            if (aux1 != 4){
                lista.add(new Carta(Character.getNumericValue(aux1), Character.getNumericValue(aux2), imagen));
            }else{
                lista.add(new Tratamiento(Character.getNumericValue(aux2), imagen));
            }
        }
        
        mano.clear();
        mano.addAll(lista);
        lista.clear();
        
    }
    
    /*public void Prueba(){
        tablero.add("1:1");
        tablero.add("2:4");
        tablero.add("3:2");
        tablero.add("4:4");
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
