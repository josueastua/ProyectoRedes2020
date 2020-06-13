package virus.util;

import javafx.scene.image.Image;

public class Carta {
    private int tipo;
    private int color;
    private Image imagen;
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
    
    public void imprimirInfo(){
        System.out.println("Color :" + color + ", Tipo: " + tipo);
    }
    
}
