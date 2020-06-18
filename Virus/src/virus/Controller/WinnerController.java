/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.Controller;

import static java.lang.System.exit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import virus.Controller.Controller;
import virus.util.AppContext;
import virus.util.Carta;
import virus.util.Jugador;

/**
 * FXML Controller class
 *
 * @author IVAN
 */
public class WinnerController extends Controller implements Initializable {

    @FXML
    private ImageView imv_sd;
    @FXML
    private ImageView imv_si;
    @FXML
    private ImageView imv_ii;
    @FXML
    private ImageView imv_id;
    @FXML
    private Label lblNombre;
    @FXML
    private Button btn_Salir;
    @FXML
    private GridPane gpCartas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void accionSalir(ActionEvent event) {
        exit(1);
    }

    @Override
    public void initialize() {
        this.getStage().setOnCloseRequest( close -> {
            close.consume();
        });
        Jugador ganador = (Jugador) AppContext.getInstance().get("Ganador");
        lblNombre.setText(ganador.getNick());
        mostrarWinner(ganador);
    }
    
    public void mostrarWinner(Jugador winner){
        ImageView imv = null;
        Carta[][] tab = winner.getMatTablero();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 5; j++){
                if(tab[j][i] != null)
                    imv = new ImageView(tab[j][i].getImagen());
                    imv.setFitHeight(30);
                    imv.setFitWidth(60);
                    gpCartas.add(imv, i, j);
            }
        }
    }
}
