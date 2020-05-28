/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import virus.socket.Conexion;

/**
 *
 * @author IVAN
 */
public class JuegoController extends Controller implements Initializable {

    @FXML private BorderPane bp_root;
    @FXML private HBox vb_informacion;
    @FXML private HBox vb_control;
    @FXML private VBox vb_principal;
    @FXML private VBox vb_jugadores;
    @FXML private Button btn_listo;
    Conexion con;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = new Conexion();
    }    
    
    /**
     * En este metodo se añadiran listener para ajustar la pantalla
     */
    public void addEvents(){
        
    }

    @FXML
    private void accionListo(ActionEvent event) {
//        con.accionEnviar("Hola Mundo".toUpperCase());
    }

    @Override
    public void initialize() {
    }

}
