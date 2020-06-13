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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import virus.socket.Conexion;
import virus.util.AppContext;
import virus.util.Carta;
import virus.util.Cuerpo;
import virus.util.FlowController;
import virus.util.Jugador;
import virus.util.Marco_Carta;
import virus.util.Mensaje;
import virus.util.Tratamiento;

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
    private TranslateTransition ttrat = new TranslateTransition(Duration.seconds(0.6));
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
    ArrayList<Object> mazo_img = new ArrayList<>();
    ArrayList<String> descartes = new ArrayList<>();
    ArrayList<Object> descartes_img = new ArrayList<>();
    Jugador player = null;
    int turno = 1;
    int oponente = 0;
    Marco_Carta carta1, carta2, mazo2, descartes2;
    Cuerpo[][] tableroJugador = new Cuerpo[3][5];
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(infoGridPane());
        con = new Conexion();
        menu = Boolean.TRUE;
        cargarCartas();
        initTraslateTransition();
        men = new Mensaje();
        AppContext.getInstance().set("Primer", null);
        AppContext.getInstance().set("Segundo", null);
        men = new Mensaje();
    }    

    public void cargarCartas(){
        player = (Jugador) AppContext.getInstance().get("Jugador");
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
            else if(i == 3){
                mazo2 = new Marco_Carta("Mazo", player);
                mazo2.setImage(new Image("virus/resources/Dorso.jpg"));
                gpManoyMazo.add(mazo2, i, 0);
            }else{
                descartes2 = new Marco_Carta("Descartes", player);
                gpManoyMazo.add(descartes2, i, 0);
            }
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
    
    public void actualizarJuego(){
        Platform.runLater( () -> {
            conseguirImagenes();
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
        if(player == null)
            player = (Jugador) AppContext.getInstance().get("Jugador");
        AppContext.getInstance().set("Juego", FlowController.getInstance().getController("Juego"));
        ArrayList<Jugador> jug = (ArrayList<Jugador>) AppContext.getInstance().get("Jugadores");
        jug.forEach( (jugador) -> {
            jugador.convertirCarta();
            if(!jugador.getId().equals(player.getId()))
                oponentes.add(jugador);
        });
        conseguirImagenes();
        
    }

    private void conseguirImagenes(){
        mazo = (ArrayList<String>) AppContext.getInstance().get("Mazo");
        mazo_img.clear();
        mazo.forEach((carta) -> {
            String dato = (String) carta;
            char aux1 = dato.charAt(0);
            char aux2 = dato.charAt(2);
            Image imagen = AppContext.getInstance().getCarta(dato);
            if(aux1 != 4){
                mazo_img.add(new Carta(Character.getNumericValue(aux1), Character.getNumericValue(aux2), imagen));
            }else{
                mazo_img.add(new Tratamiento(Character.getNumericValue(aux2), imagen));
            }
        });
        lblPlayer.setText("ID: "+player.getId()+" Nick: "+player.getNick());
        int index = 0;
        ObservableList<Node> mano = gpManoyMazo.getChildren();
        System.out.println(player.getMano().size());
        for (Object mano1 : player.getMano()) {
            Marco_Carta aux = (Marco_Carta)mano.get(index);
            if(mano1.getClass().equals(Carta.class)){
                Carta carta = (Carta)mano1;
                aux.setCarta(carta);
                aux.setImage(carta.getImagen());
            }else{
                Tratamiento carta = (Tratamiento)mano1;
                aux.setTratamiento(carta);
                aux.setImage(carta.getImagen());
            }
            index++;
        }
        System.out.println(index);
    }
    
    public String infoGridPane(){
        
        String info = "";
        Marco_Carta aux;
        int cont = 0;
        int total = 0;
        for (Node nodo : gpCartasOponente.getChildren()){
            aux = (Marco_Carta) nodo;
            if(aux.getCarta() != null){
                info += String.valueOf(aux.getCarta().getTipo()) + ":" + String.valueOf(aux.getCarta().getColor());
                cont++;
                total++;
            }else{
                info += "0";
                cont++;
                total++;
            }
            
            if(cont == 5 && total < 15){
                info += " ";
                cont = 0;
            }
            
            if(cont > 0 && cont < 5 && total < 15){
                info += "-";
            }
            
        }
        return info;
    }
    
    @FXML
    private void accionJugada(ActionEvent event) {
        String mensaje = player.infoJugador();
        for(Jugador opo: oponentes) 
            mensaje += "/"+opo.infoJugador();
        for(String carta: mazo){
            mensaje += "/"+carta;
        }
        if(!descartes.isEmpty()){
            for(String carta: descartes)
                mensaje += "/"+carta;
        }
        mensaje += "/"+AppContext.getInstance().get("Especial")+"/"+AppContext.getInstance().get("ID");
    }

    @FXML
    private void accionSiguente(ActionEvent event) {
        if(oponente == oponentes.size() - 1){
            oponente = 0;
        }else{
            oponente++;
        }
    }

    @FXML
    private void accionShow(ActionEvent event) {
        if(!menu){
            vb_contenerdor_oponentes.setPrefWidth(320);
            vb_contenerdor_oponentes.getChildren().add(vb_oponente);
            vb_player.setPrefWidth(root.getWidth()-320);
            tt.setByX(-450);
            tt.setToX(0);
            btnShow.setText("Ocultar Oponente");
        }else{
            tt.setByX(0);
            tt.setToX(-450);
            btnShow.setText("Mostrar Oponente");
        }
        tt.play();
        menu = !menu;
    }

    @FXML
    private void accionGridPaneOponente(MouseEvent event) {
        System.out.println("GPO");
        if(AppContext.getInstance().get("Segundo") != null){
            
        }
    }

    @FXML
    private void accionGripPanePlayer(MouseEvent event) {
        System.out.println("GPP");
        if(AppContext.getInstance().get("Segundo") != null){
            
        }
    }

    @FXML
    private void accionManoMazoDescartes(MouseEvent event) {
        System.out.println("GPMD");
        if(AppContext.getInstance().get("Segundo") != null){
            
        }
    }
    
    public void hacerJugada(){
        Marco_Carta marco1 = (Marco_Carta) AppContext.getInstance().get("Primero");
        Marco_Carta marco2 = (Marco_Carta) AppContext.getInstance().get("Segundo");
        switch(marco1.getCarta().getTipo()){
            //La carta es un organo
            case 1:{
                if(gpCartas.getChildren().contains(marco2)){
                   if(GridPane.getRowIndex(marco2) == 0){
                       if(verificarOrganoRepetido(marco1)){
                           marco2.setCarta(marco1.getCarta());
                           marco2.setImage(marco1.getCarta().getImagen());
                           marco1.setCarta(null);
                       }
                    }
                }
                break;
            }
            case 2:{
                if(gpCartas.getChildren().contains(marco2)){
                   if(GridPane.getRowIndex(marco2) == 1){
                       if(verificarOrganoRepetido(marco1)){
                           marco2.setCarta(marco1.getCarta());
                           marco2.setImage(marco1.getCarta().getImagen());
                           marco1.setCarta(null);
                       }
                    }
                }
                break;
            }
            case 3:{
                break;
            }
        }
    }
    
    public boolean verificarOrganoRepetido(Marco_Carta carta){
        Marco_Carta aux;
        for(Node nodo: gpCartas.getChildren()){
            aux = (Marco_Carta) nodo;
            if(aux.getCarta() != null){
                if(aux.getCarta().getColor() == carta.getCarta().getColor() && aux.getCarta().getTipo() == carta.getCarta().getTipo()){
                    return false;
                } 
            }
        }
        return true;
    }
    
    public void cartaTratamiento(){
        Marco_Carta trata = (Marco_Carta) AppContext.getInstance().get("Tratamiento");
        Tratamiento tra = trata.getTratamiento();
        String especial = "";
        switch(tra.getTipo()){
            case 1:
                break;
        }
        ttrat.setAutoReverse(false);//Para que no se devuelva
        ttrat.setCycleCount(1);
        ttrat.setDelay(Duration.ONE);
        ttrat.setNode(trata);
        ttrat.setByX(400);
        ttrat.setToX(trata.getLayoutY());
        ttrat.play();
    }
}
