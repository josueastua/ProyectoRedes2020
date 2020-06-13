
package virus.util;

import javafx.scene.image.Image;

public class Tratamiento {
    private int tipo;
    private Image imagen;
    private String representacion;
    
    Tratamiento(){
        
    }
    
    public Tratamiento(int tipo, Image imagen){
        this.tipo = tipo;
        this.imagen = imagen;
        representacionTratamiento();
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

    public String getRepresentacion() {
        return representacion;
    }
    
    public void representacionTratamiento(){
        representacion = "4:"+ String.valueOf(tipo);
    }
    
    public void imprimirInfo(){
        System.out.println("Tipo: " + tipo);
    }
   
}
