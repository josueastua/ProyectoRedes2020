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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import virus.socket.Conexion;
import virus.util.Marco_Carta;

/**
 *
 * @author IVAN
 */
public class JuegoController extends Controller implements Initializable {
    Conexion con;
    @FXML private GridPane gpCartas;
    @FXML private HBox hbCartas;
    @FXML private HBox hbMano;
    @FXML private Button btnJugada;
    @FXML private Label lblOponente;
    @FXML private GridPane gpCartasOponente;
    @FXML private Button btn_Oponente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = new Conexion();
        cargarCartas();
    }    
    
    /**
     * En este metodo se añadiran listener para ajustar la pantalla
     */
    public void addEvents(){
        
    }

    public void cargarCartas(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 5; j++){
                gpCartasOponente.add(new Marco_Carta(), i, j);
            }
        }
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                gpCartas.add(new Marco_Carta(), i, j);
            }
        }
        for(int i = 0; i < 5; i++)
            hbMano.getChildren().add(new Marco_Carta());
        
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void accionJugada(ActionEvent event) {
    }

    @FXML
    private void accionSiguente(ActionEvent event) {
    }

}
