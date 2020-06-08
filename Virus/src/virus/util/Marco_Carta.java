/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.util;


import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 * Esta clase se usara para agregar a todos los
 * ImageView los eventos DragAndDrop
 * @author IVAN
 */

public class Marco_Carta extends ImageView{
    
    public Marco_Carta(){
        asignarEventos();
    }
    
    private void asignarEventos(){
        
        this.setOnDragDetected((MouseEvent t) -> {
            Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("MOV");
            db.setContent(content);
            t.consume();
        });
        
        this.setOnDragOver((DragEvent t1)-> {
            t1.acceptTransferModes(TransferMode.MOVE);
            t1.consume();
        });
        
        this.setOnDragDropped((DragEvent t2) -> {
           //Pendiente
        });
    }
}
