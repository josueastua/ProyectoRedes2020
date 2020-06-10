
package virus.util;

import javafx.scene.image.Image;

public class Tratamiento {
    private int tipo;
    private Image imagen;
    
    Tratamiento(){
        
    }
    
    Tratamiento(int tipo, Image imagen){
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public Integer getTipo() {
        return tipo;
    }
    
    public void imprimirInfo(){
        System.out.println("Tipo: " + tipo);
    }
   
}
