package virus.util;

import java.util.ArrayList;

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
    
    public void prueba(){
      
        mano.add(new Carta(1,1));
        mano.add(new Carta(4,2));
        mano.add(new Carta(2,3));
        
        /*tablero.add(new Carta(1,1));
        tablero.add(new Carta(3,1));
        tablero.add(new Carta(2,4));
        tablero.add(new Carta(1,5));*/
        System.out.println(infoJugador());
    }
}
