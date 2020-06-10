/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.util;


import javafx.scene.layout.Pane;

/**
 * Esta clase se usara para agregar a todos los
 * ImageView los eventos DragAndDrop
 * @author IVAN
 */

public class Marco_Carta extends Pane{
    
    Jugador player;
    
    public Marco_Carta(){
        asignarEventos();
    }
    
    public Marco_Carta(String id, Jugador player){
        this.setId(id);
        asignarEventos();
        this.player = player;
    }
    
    private void asignarEventos(){
        
        this.setOnMouseClicked( click -> {
            int turno = (int) AppContext.getInstance().get("Turno");
            if(!this.getId().equals("Mazo") && !this.getId().equals("Descartes") && player.getTurno() == turno)
                this.setStyle("-fx-background-color: red");
        });
        
        
    }
}
