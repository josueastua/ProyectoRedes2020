/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.Controller;

import static java.lang.System.exit;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import virus.socket.Conexion;
import virus.util.AppContext;
import virus.util.FlowController;

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
    Conexion con;
    Timer timer = null;
    @FXML
    private TextField txt_Nickname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addEvents();
        AppContext.getInstance().set("Conexion", con);
        con = new Conexion();
        
    }    

    @FXML
    private void accionIngresar(ActionEvent event) {
        if(txt_Nickname.getText() != null){
            con.accionEnviar("1", txt_Nickname.getText());
        }
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
    
    public void hilo(){
        timer = new Timer();
        AppContext.getInstance().set("Timer", timer);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("HILO");
                con.accionEnviar((String)AppContext.getInstance().get("Clave"), (String)AppContext.getInstance().get("Mensaje"));
            }
        }, 5000, 5000);
    }
    
    @Override
    public void initialize() {
        ajustarAltura();
        ajustarAncho();
        AppContext.getInstance().set("Ingreso", FlowController.getInstance().getController("Ingreso"));
        AppContext.getInstance().set("Clave", "2");
        AppContext.getInstance().set("Mensaje", "");
        if(this.getStage().getOnCloseRequest() == null){
            System.out.println("OnCloseREQUEST");
            this.getStage().setOnCloseRequest( close -> {
                System.out.println("Ok");
                exit(1);
            });
        }
    }
    
}
