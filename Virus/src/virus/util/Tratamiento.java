
package virus.util;

import javafx.scene.image.Image;

public class Tratamiento {
    private int tipo;
    private Image imagen;
    
    Tratamiento(){
        
    }
    
    public Tratamiento(int tipo, Image imagen){
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public Integer getTipo() {
        return tipo;
    }
    
    public Image getImagen(){
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
    public void imprimirInfo(){
        System.out.println("Tipo: " + tipo);
    }
   
}
