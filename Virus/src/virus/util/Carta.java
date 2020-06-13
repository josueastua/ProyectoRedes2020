package virus.util;

import javafx.scene.image.Image;

public class Carta {
    private int tipo;
    private int color;
    private Image imagen;
    private String representacion;
    
    Carta(){
        
    }
    
    public Carta(int tipo, int color, Image imagen){
        this.tipo = tipo;
        this.color = color;
        this.imagen = imagen;
    }

    public int getTipo() {
        return tipo;
    }

    public Integer getColor() {
        return color;
    }
    
    public Image getImagen(){
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public String getRepresentacion() {
        return representacion;
    }
    
    
    
    public static Carta crearCarta(String info){
        if(info.equals("0")){
            return null;
        }else{
            char tipo = info.charAt(0);
            char color = info.charAt(2);
            Carta carta = new Carta(Character.getNumericValue(tipo),Character.getNumericValue(color),AppContext.getInstance().getCarta(info));
            return carta;
        }
    }
    
    public void representacionCarta(){
        representacion = String.valueOf(tipo)+":"+String.valueOf(color);
    }
    
    public void imprimirInfo(){
        System.out.println("Color :" + color + ", Tipo: " + tipo);
    }
    
    @Override
    public String toString(){
        return "Tipo: " + tipo + ", Color: " + color;
    }
    
}
