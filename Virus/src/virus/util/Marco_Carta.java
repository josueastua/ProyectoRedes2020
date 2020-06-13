/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.util;


import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Esta clase se usara para agregar a todos los
 * ImageView los eventos DragAndDrop
 * @author IVAN
 */

public class Marco_Carta extends Pane{
    
    Jugador player;
    Carta carta;
    Tratamiento tratamiento;
    GridPane oponent, tablero;
    Mensaje men;
    
    public Marco_Carta(){
        asignarEventos();
    }
    
    public Marco_Carta(String id, Jugador player){
        men = new Mensaje();
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

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Jugador getPlayer() {
        return player;
    }

    public void setPlayer(Jugador player) {
        this.player = player;
    }

    public GridPane getOponent() {
        return oponent;
    }

    public void setOponent(GridPane oponent) {
        this.oponent = oponent;
    }

    public GridPane getTablero() {
        return tablero;
    }

    public void setTablero(GridPane tablero) {
        this.tablero = tablero;
    }
    
    private void asignarEventos(){
        
        this.setOnMouseClicked( click -> {
            int turno = (int) AppContext.getInstance().get("Turno");
            if(player.getTurno() == turno){
                if(!this.getId().equals("Mazo") && !this.getId().equals("Descartes") && this.carta != null){
                    //this.setStyle("-fx-background-color: red");
                    if(AppContext.getInstance().get("Primero") == null){
                        Marco_Carta aux;
                        for(Object obj: player.getMano()){
                            aux = (Marco_Carta)obj;
                            if(aux == this){
                                AppContext.getInstance().set("Primero", this);
                            }
                        }
                    }else{
                        Marco_Carta aux;
                        for(Object obj: oponent.getChildren()){
                            aux = (Marco_Carta)obj;
                            if(aux == this && aux.getCarta() == null)
                                AppContext.getInstance().set("Segundo", this);
                        }
                        for(Object obj: tablero.getChildren()){
                            aux = (Marco_Carta)obj;
                            if(aux == this && aux.getCarta() == null)
                                AppContext.getInstance().set("Segundo", this);
                        }
                    }
                }else if(this.getId().equals("Mazo")){
                    Carta aux, aux2;
                    Tratamiento aux3;
                    for(Object obj: player.getMano()){
                        aux = (Carta)obj;
                        if(aux.getImagen()== null){
                            ArrayList<Object> mazo = (ArrayList<Object>) AppContext.getInstance().get("Mazo");
                            if(mazo.get(0).getClass().equals(Carta.class)){
                                aux2 = (Carta)mazo.get(0);
                                aux.setImagen(aux2.getImagen());
                                mazo.remove(mazo.get(0));
                            }else if(mazo.get(0).getClass().equals(Tratamiento.class)){
                                aux3 = (Tratamiento)mazo.get(0);
                                aux.setImagen(aux3.getImagen());
                                mazo.remove(mazo.get(0));
                            }
                        }
                    }
                }else if(this.tratamiento != null){
                    AppContext.getInstance().set("Tratamiento", this);
                }
            }else{
                men.show(Alert.AlertType.ERROR, "JUGAR", "Aun no es su turno");
            }
        });
        
        
    }
}
