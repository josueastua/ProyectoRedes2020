/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.util;

import javafx.scene.image.ImageView;

/**
 *
 * @author IVAN
 */
public class Cuerpo {
    private Object carta;
    private ImageView image;

    public Cuerpo(Carta carta, ImageView image) {
        this.carta = carta;
        this.image = image;
    }

    public Object getCarta() {
        return carta;
    }

    public void setCarta(Object carta) {
        this.carta = carta;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
    
    
}
