/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.Controller;

import com.sun.imageio.plugins.jpeg.JPEG;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import virus.util.AppContext;
import virus.util.Carta;
import virus.util.Cuerpo;
import virus.util.Jugador;

/**
 * FXML Controller class
 *
 * @author Dios
 */
public class EspecialesController extends Controller implements Initializable {

    @FXML
    private ImageView ivFondo1;
    @FXML
    private ImageView ivFondo2;
    @FXML
    private ImageView ivFondo3;
    @FXML
    private ImageView ivFondo4;
    @FXML
    private ComboBox<Jugador> cbJugador1;
    @FXML
    private ComboBox<Jugador> cbJugador2;
    @FXML
    private ImageView pri00;
    @FXML
    private ImageView pri01;
    @FXML
    private ImageView pri02;
    @FXML
    private ImageView pri10;
    @FXML
    private ImageView pri11;
    @FXML
    private ImageView pri12;
    @FXML
    private ImageView pri20;
    @FXML
    private ImageView pri21;
    @FXML
    private ImageView pri22;
    @FXML
    private ImageView pri30;
    @FXML
    private ImageView pri31;
    @FXML
    private ImageView pri32;
    @FXML
    private ImageView pri40;
    @FXML
    private ImageView pri41;
    @FXML
    private ImageView pri42;
    @FXML
    private ImageView seg00;
    @FXML
    private ImageView seg01;
    @FXML
    private ImageView seg02;
    @FXML
    private ImageView seg10;
    @FXML
    private ImageView seg11;
    @FXML
    private ImageView seg12;
    @FXML
    private ImageView seg20;
    @FXML
    private ImageView seg21;
    @FXML
    private ImageView seg22;
    @FXML
    private ImageView seg30;
    @FXML
    private ImageView seg31;
    @FXML
    private ImageView seg32;
    @FXML
    private ImageView seg40;
    @FXML
    private ImageView seg41;
    @FXML
    private ImageView seg42;
    
    
    Cuerpo matPrimero[][] = new Cuerpo[5][3];
    Cuerpo matSegundo[][] = new Cuerpo[5][3];
    ArrayList<Jugador> jugadores;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matPrimero[0][0] = new Cuerpo(null,pri00); matPrimero[0][1] = new Cuerpo(null,pri01); matPrimero[0][2] = new Cuerpo(null,pri02);
        matPrimero[1][0] = new Cuerpo(null,pri10); matPrimero[1][1] = new Cuerpo(null,pri11); matPrimero[1][2] = new Cuerpo(null,pri12);
        matPrimero[2][0] = new Cuerpo(null,pri20); matPrimero[2][1] = new Cuerpo(null,pri21); matPrimero[2][2] = new Cuerpo(null,pri22);
        matPrimero[3][0] = new Cuerpo(null,pri30); matPrimero[3][1] = new Cuerpo(null,pri31); matPrimero[3][2] = new Cuerpo(null,pri32);
        matPrimero[4][0] = new Cuerpo(null,pri40); matPrimero[4][1] = new Cuerpo(null,pri41); matPrimero[4][2] = new Cuerpo(null,pri42);
        
        matSegundo[0][0] = new Cuerpo(null,seg00); matSegundo[0][1] = new Cuerpo(null,seg01); matSegundo[0][2] = new Cuerpo(null,seg02);
        matSegundo[1][0] = new Cuerpo(null,seg10); matSegundo[1][1] = new Cuerpo(null,seg11); matSegundo[1][2] = new Cuerpo(null,seg12);
        matSegundo[2][0] = new Cuerpo(null,seg20); matSegundo[2][1] = new Cuerpo(null,seg21); matSegundo[2][2] = new Cuerpo(null,seg22);
        matSegundo[3][0] = new Cuerpo(null,seg30); matSegundo[3][1] = new Cuerpo(null,seg31); matSegundo[3][2] = new Cuerpo(null,seg32);
        matSegundo[4][0] = new Cuerpo(null,seg40); matSegundo[4][1] = new Cuerpo(null,seg41); matSegundo[4][2] = new Cuerpo(null,seg42);
        
        ArrayList<Jugador> lista = (ArrayList<Jugador>) AppContext.getInstance().get("Jugadores");
        cbJugador1.getItems().addAll(lista);
        cbJugador2.getItems().addAll(lista);
    }
    
    public void cargarImagenes(int jug){
        if(jug == 1){
            
        }
    }

    @FXML
    private void ElegirJugador(ActionEvent event) {
        
    }

    @Override
    public void initialize() {
        
    }
}
