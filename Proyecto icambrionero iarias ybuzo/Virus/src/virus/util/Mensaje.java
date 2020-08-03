/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.util;

import java.io.FileNotFoundException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;

/**
 *
 * @author ccarranza
 */
public class Mensaje {
    

    public void show(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void showModal(AlertType tipo, String titulo, Window padre, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.initOwner(padre);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    public Boolean showConfirmation(String titulo, Window padre, String mensaje) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.initOwner(padre);
        alert.setContentText(mensaje);
        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == ButtonType.OK;
    }
    
    /*
    Muestra un alert de tipo confirmacion modificado
    */
    public int showOptionBotton(String titulo, Window padre, String mensaje){
        int retorno = 0;
        ButtonType bt_puesto = new ButtonType("POR PUESTO"),
                   bt_empleado = new ButtonType("POR EMPLEADO"),
                   bt_cancelar = new ButtonType("CANCELAR");
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.initOwner(padre);
        alert.setContentText(mensaje);
        alert.getButtonTypes().setAll(bt_empleado, bt_puesto, bt_cancelar);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == bt_empleado){
            retorno = 1;
        }else if(result.get() == bt_puesto){
            retorno = 2;
        }else{
            retorno = 0;
        }
        return retorno;
    }
    
    /*
    Muestra una alert con un text area que contiene información
    */
    public void showAlertWithTextArea(String titulo, Window padre, String mensaje){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText("Informacion");
        
        Label label = new Label("Informacion Importante");

        TextArea textArea = new TextArea(mensaje);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        textArea.setStyle("-fx-font: bold 20px 'Bodoni MT'");
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        
        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }

    
    public int showOptionBotton2(String titulo, Window padre, String mensaje){
        int retorno = 0;
        ButtonType bt_puesto = new ButtonType("TODOS"),
                   bt_empleado = new ButtonType("POR EMPLEADO"),
                   bt_cancelar = new ButtonType("CANCELAR");
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.initOwner(padre);
        alert.setContentText(mensaje);
        alert.getButtonTypes().setAll(bt_empleado, bt_puesto, bt_cancelar);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == bt_empleado){
            retorno = 1;
        }else if(result.get() == bt_puesto){
            retorno = 2;
        }else{
            retorno = 0;
        }
        return retorno;
    }
}
