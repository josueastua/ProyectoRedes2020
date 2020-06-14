/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author IVAN
 */
public class Cuerpo {
    private Carta carta;
    private ImageView image;

    public Cuerpo(Carta carta, ImageView image) {
        this.carta = carta;
        this.image = image;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }
    
    
}
