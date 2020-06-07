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
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
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
    @FXML private Label lblPlayer;
    @FXML private Button btnShow;
    @FXML private VBox vb_contenerdor_oponentes;
    @FXML private VBox vb_oponente;
    private TranslateTransition tt = new TranslateTransition(Duration.seconds(0.6));
    private Boolean menu; 
    @FXML private VBox vb_contenedor_principal;
    @FXML private HBox hb_titulo;
    @FXML private HBox hb_contenedor_juego;
    @FXML private VBox vb_player;
    @FXML private AnchorPane root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = new Conexion();
        menu = Boolean.TRUE;
        cargarCartas();
        initTraslateTransition();
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
    
    private void initTraslateTransition(){
        tt.setAutoReverse(false);//Para que no se devuelva
        tt.setCycleCount(1);
        tt.setDelay(Duration.ONE);
        tt.setNode(vb_contenerdor_oponentes);
        tt.setOnFinished( event -> {
            if(!menu){
                vb_contenerdor_oponentes.setPrefWidth(0);
                vb_contenerdor_oponentes.getChildren().clear();
                vb_contenerdor_oponentes.setLayoutX(-320);
                vb_player.setPrefWidth(root.getWidth());
                        
            }
        });
        
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

    @FXML
    private void accionShow(ActionEvent event) {
        if(!menu){
            vb_contenerdor_oponentes.setPrefWidth(320);
            vb_contenerdor_oponentes.getChildren().add(vb_oponente);
            vb_player.setPrefWidth(root.getWidth()-320);
            tt.setByX(-320);
            tt.setToX(0);
            btnShow.setText("Ocultar Oponente");
        }else{
            tt.setByX(0);
            tt.setToX(-320);
            btnShow.setText("Mostrar Oponente");
        }
        tt.play();
        menu = !menu;
    }

}
