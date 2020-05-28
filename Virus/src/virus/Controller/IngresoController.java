/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author IVAN
 */
public class IngresoController extends Controller implements Initializable {

    @FXML
    private AnchorPane ap_root;
    @FXML
    private ImageView imv_sd;
    @FXML
    private ImageView imv_si;
    @FXML
    private ImageView imv_ii;
    @FXML
    private ImageView imv_id;
    @FXML
    private VBox vb_contenerdor;
    @FXML
    private Button btn_Ingresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void accionIngresar(ActionEvent event) {
    }
    
    private void addEvents(){
        ap_root.widthProperty().addListener( w -> {
            ajustarAncho();
        });
        ap_root.heightProperty().addListener( h -> {
            ajustarAltura();
        });
    }

    private void ajustarAncho(){
        double a = ap_root.getWidth(); 
        imv_id.setFitWidth(a/2);
        imv_ii.setFitWidth(a/2);
        imv_sd.setFitWidth(a/2);
        imv_si.setFitWidth(a/2);
        imv_sd.setLayoutX(a/2);
        imv_id.setLayoutX(a/2);
    }
    
    private void ajustarAltura(){
        double l = ap_root.getHeight();
        imv_id.setFitHeight(l/2);
        imv_ii.setFitHeight(l/2);
        imv_sd.setFitHeight(l/2);
        imv_si.setFitHeight(l/2);
        imv_id.setLayoutY(l/2);
        imv_ii.setLayoutY(l/2);
    }
    
    @Override
    public void initialize() {
        ajustarAltura();
        ajustarAncho();
    }
    
}
