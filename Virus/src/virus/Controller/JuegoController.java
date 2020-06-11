/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import virus.socket.Conexion;
import virus.util.AppContext;
import virus.util.FlowController;
import virus.util.Jugador;
import virus.util.Marco_Carta;
import virus.util.Mensaje;

/**
 *
 * @author IVAN
 */
public class JuegoController extends Controller implements Initializable {
    Conexion con;
    @FXML private GridPane gpCartas;
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
    @FXML
    private GridPane gpManoyMazo;
    @FXML
    private ImageView imv_fondo;
    Mensaje men;
    
    ArrayList<Jugador> oponentes = new ArrayList<>(); 
    ArrayList<String> mazo = new ArrayList<>();
    ArrayList<Image> mazo_img = new ArrayList<>();
    ArrayList<String> descartes = new ArrayList<>();
    ArrayList<Image> descartes_img = new ArrayList<>();
    Jugador player = null;
    int turno = 1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = new Conexion();
        menu = Boolean.TRUE;
        cargarCartas();
        initTraslateTransition();
        addEvents();
        men = new Mensaje();
    }    
    
    /**
     * En este metodo se añadiran listener para ajustar la pantalla
     */
    public void addEvents(){
        root.widthProperty().addListener( w -> {
            ajustarAncho(root.getWidth());
        });
        root.heightProperty().addListener( h -> {
            ajustarLargo(root.getHeight());
        });
    }
    
    public void ajustarAncho(Double ancho){
        imv_fondo.setFitWidth(ancho);
        hb_titulo.setPrefWidth(ancho);
        lblPlayer.setPrefWidth(ancho - 227);
        if(menu){
            vb_player.setPrefWidth(ancho - 320);
        }else{
            vb_player.setPrefWidth(ancho);
        }
    }
    
    public void ajustarLargo(Double largo){
        imv_fondo.setFitHeight(largo);
        vb_oponente.setPrefHeight(largo - 80);
        hb_contenedor_juego.setPrefHeight(largo - 80);
        vb_contenedor_principal.setPrefHeight(largo - 80);
        vb_contenerdor_oponentes.setPrefHeight(largo - 80);
        vb_player.setPrefHeight(largo - 80);
        gpCartas.setPrefHeight(largo - 80 - (78) - (140));
        gpCartasOponente.setPrefHeight(largo - 80 - (85) - (80));
    }

    public void cargarCartas(){
        for(int i = 0; i < 3; i++){
            for(int j = 1; j <= 5; j++){
                gpCartasOponente.add(new Marco_Carta("Carta"+i+j, player), i, j);
            }
        }
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                gpCartas.add(new Marco_Carta("Carta"+i+j, player), i, j);
            }
        }
        for(int i = 0; i < 5; i++)
            if(i < 3)
                gpManoyMazo.add(new Marco_Carta("Mano"+i, player), i, 0);
            else if(i == 3)
                gpManoyMazo.add(new Marco_Carta("Mazo", player), i, 0);
            else
                gpManoyMazo.add(new Marco_Carta("Descartes", player), i, 0);
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
                //vb_contenerdor_oponentes.setLayoutX(-320);
                vb_player.setPrefWidth(root.getWidth());
                        
            }
        });
        
    }
    
    public void informarCartaEspecial(String carta, String idPlayer){
        Platform.runLater( () -> {
            String mensaje = "Un jugador te aplico ";
            Boolean mostrar = false;
            switch(carta){
                case "1":
                    mensaje += "Ladron de Organos";
                    break;
                case "2":
                    mensaje += "Trasplante de Organos";
                    break;
                case "3":
                    mensaje += "Infeccion";
                    break;
                case "4":
                    mensaje += "Guante de latex";
                    mostrar = true;
                    break;
                case "5":
                    mensaje += "Error medico";
                    break;
                default:
                    break;
            }
            if(!mostrar){
                Jugador player = (Jugador) AppContext.getInstance().get("Jugador");
                mostrar = player.getId().equals(idPlayer);
            }
            if(mostrar){
                Mensaje men = new Mensaje();
                men.show(Alert.AlertType.INFORMATION, carta, mensaje);
            }
        });   
    }
    
    @Override
    public void initialize() {
        AppContext.getInstance().set("Juego", FlowController.getInstance().getController("Juego"));
        player = (Jugador) AppContext.getInstance().get("Jugador");
        ArrayList<Jugador> jug = (ArrayList<Jugador>) AppContext.getInstance().get("Jugadores");
        conseguirImagenes();
        lblPlayer.setText(player.getId());
    }

    private void conseguirImagenes(){
        mazo = (ArrayList<String>) AppContext.getInstance().get("Mazo");
        mazo_img.clear();
        mazo.forEach((carta) -> {
            mazo_img.add(AppContext.getInstance().getCarta(carta));
        });
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
