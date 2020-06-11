/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.util;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Esta clase se usara para agregar a todos los
 * ImageView los eventos DragAndDrop
 * @author IVAN
 */

public class Marco_Carta extends Pane{
    
    Jugador player;
    Carta carta;
    
    public Marco_Carta(){
        asignarEventos();
    }
    
    public Marco_Carta(String id, Jugador player){
        this.setPrefHeight(120);
        this.setPrefWidth(110);
        this.setId(id);
        asignarEventos();
        this.player = player;
    }
    
    public void setImage(Image image){
        ImageView imv = new ImageView(image);
        imv.setFitHeight(120);
        imv.setFitWidth(110);
        imv.preserveRatioProperty().set(Boolean.FALSE);
        imv.smoothProperty().set(Boolean.FALSE);
        this.getChildren().clear();
        this.getChildren().add(imv);
    }
    
    public Carta getCarta(){
        return carta;
    }
    
    public void setCarta(Carta carta){
        this.carta = carta;
    }
    
    private void asignarEventos(){
        
        this.setOnMouseClicked( click -> {
            int turno = (int) AppContext.getInstance().get("Turno");
            if(!this.getId().equals("Mazo") && !this.getId().equals("Descartes") && player.getTurno() == turno)
                this.setStyle("-fx-background-color: red");
        });
        
        
    }
}
