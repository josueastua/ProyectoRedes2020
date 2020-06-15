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
import java.util.Timer;
import java.util.TimerTask;
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
import javafx.scene.control.ComboBox;
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
    ArrayList<Object> mazo_img = new ArrayList<>();
    ArrayList<String> descartes = new ArrayList<>();
    ArrayList<Object> descartes_img = new ArrayList<>();
    Jugador player = null;
    int turno = 1;
    int oponente = 0;
    ImageView primero = null;
    ImageView segundo = null;
    int click = 0;
    
    
    @FXML
    private ImageView ivOP00;
    @FXML
    private ImageView ivOP01;
    @FXML
    private ImageView ivOP02;
    @FXML
    private ImageView ivOP10;
    @FXML
    private ImageView ivOP11;
    @FXML
    private ImageView ivOP12;
    @FXML
    private ImageView ivOP20;
    @FXML
    private ImageView ivOP21;
    @FXML
    private ImageView ivOP22;
    @FXML
    private ImageView ivOP30;
    @FXML
    private ImageView ivOP31;
    @FXML
    private ImageView ivOP32;
    @FXML
    private ImageView ivOP40;
    @FXML
    private ImageView ivOP41;
    @FXML
    private ImageView ivOP42;
    @FXML
    private ImageView ivTab00;
    @FXML
    private ImageView ivTab01;
    @FXML
    private ImageView ivTab02;
    @FXML
    private ImageView ivTab03;
    @FXML
    private ImageView ivTab04;
    @FXML
    private ImageView ivTab10;
    @FXML
    private ImageView ivTab11;
    @FXML
    private ImageView ivTab12;
    @FXML
    private ImageView ivTab13;
    @FXML
    private ImageView ivTab14;
    @FXML
    private ImageView ivTab20;
    @FXML
    private ImageView ivTab21;
    @FXML
    private ImageView ivTab22;
    @FXML
    private ImageView ivTab23;
    @FXML
    private ImageView ivTab24;
    @FXML
    private ImageView ivMano00;
    @FXML
    private ImageView ivMano01;
    @FXML
    private ImageView ivMano02;
    @FXML
    private ImageView ivMano03;
    @FXML
    private ImageView ivMano04;
    @FXML
    private ComboBox<String> cbDescartar;
    Cuerpo[][] tablero, tabOponente;
    Cuerpo[] mano;
    private Timer timer;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crearMatrices();
        con = new Conexion();
        menu = Boolean.TRUE;
        initTraslateTransition();
        addEvents();
        men = new Mensaje();
        AppContext.getInstance().set("Primer", null);
        AppContext.getInstance().set("Segundo", null);
        men = new Mensaje();
    }    
    
    public void crearMatrices(){
        tablero = new Cuerpo[3][5];
        tabOponente = new Cuerpo[5][3];
        mano = new Cuerpo[5];
        
        tablero[0][0] = new Cuerpo(null, ivTab00); tablero[0][1] = new Cuerpo(null, ivTab01); tablero[0][2] = new Cuerpo(null, ivTab02); tablero[0][3] = new Cuerpo(null, ivTab03); tablero[0][4] = new Cuerpo(null, ivTab04); 
        tablero[1][0] = new Cuerpo(null, ivTab10); tablero[1][1] = new Cuerpo(null, ivTab11); tablero[1][2] = new Cuerpo(null, ivTab12); tablero[1][3] = new Cuerpo(null, ivTab13); tablero[1][4] = new Cuerpo(null, ivTab14);
        tablero[2][0] = new Cuerpo(null, ivTab20); tablero[2][1] = new Cuerpo(null, ivTab21); tablero[2][2] = new Cuerpo(null, ivTab22); tablero[2][3] = new Cuerpo(null, ivTab23); tablero[2][4] = new Cuerpo(null, ivTab24);

        tabOponente[0][0] = new Cuerpo(null, ivOP00); tabOponente[0][1] = new Cuerpo(null, ivOP01); tabOponente[0][2] = new Cuerpo(null, ivOP02);
        tabOponente[1][0] = new Cuerpo(null, ivOP10); tabOponente[1][1] = new Cuerpo(null, ivOP11); tabOponente[1][2] = new Cuerpo(null, ivOP12);
        tabOponente[2][0] = new Cuerpo(null, ivOP20); tabOponente[2][1] = new Cuerpo(null, ivOP21); tabOponente[2][2] = new Cuerpo(null, ivOP22);
        tabOponente[3][0] = new Cuerpo(null, ivOP30); tabOponente[3][1] = new Cuerpo(null, ivOP31); tabOponente[3][2] = new Cuerpo(null, ivOP32);
        tabOponente[4][0] = new Cuerpo(null, ivOP40); tabOponente[4][1] = new Cuerpo(null, ivOP41); tabOponente[4][2] = new Cuerpo(null, ivOP42);
        
        mano[0] = new Cuerpo(null, ivMano00);
        mano[1] = new Cuerpo(null, ivMano01);
        mano[2] = new Cuerpo(null, ivMano02);
        mano[3] = new Cuerpo(null, ivMano03);
        mano[4] = new Cuerpo(null, ivMano04);
        
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
            mostrarOponente();
            mostrarTablero();
        });
    }
    
    public void informarCartaEspecial(String carta, String id1, String id2){
        Platform.runLater( () -> {
            String mensaje = "Un jugador te aplico ";
            Boolean mostrar = false;
            switch(carta){
                case "1":
                    mensaje += "Ladron de Organos";
                    if(player.getId().equals(id2) || player.getId().equals(id1)){
                        men.show(Alert.AlertType.INFORMATION, "Carta Especial", mensaje);
                        mostrar = true;
                    }
                    break;
                case "2":
                    mensaje += "Trasplante de Organos";
                    break;
                case "3":
                    mensaje += "Infeccion";
                    if(player.getId().equals(id2) || player.getId().equals(id1)){
                        men.show(Alert.AlertType.INFORMATION, "Carta Especial", mensaje);
                        mostrar = true;
                    }
                    break;
                case "4":
                    mensaje += "Guante de latex";
                    if(player.getId().equals(id2) || player.getId().equals(id1)){
                        men.show(Alert.AlertType.INFORMATION, "Carta Especial", mensaje);
                        mostrar = true;
                    }
                    break;
                case "5":
                    mensaje += "Error medico";
                    break;
                default:
                    break;
            }
            if(!mostrar){
                if(player.getId().equals(id2)){
                    men.show(Alert.AlertType.INFORMATION, "Carta Especial", mensaje);
                }
            }
        });   
    }
   
    @Override
    public void initialize() {
        cbDescartar.getItems().clear();
        cbDescartar.getItems().add("Las tres cartas");
        cbDescartar.getItems().add("La carta 1 y la carta 2");
        cbDescartar.getItems().add("La carta 1 y la carta 3");
        cbDescartar.getItems().add("La carta 2 y la carta 3");
        cbDescartar.getItems().add("La carta 1");
        cbDescartar.getItems().add("La carta 2");
        cbDescartar.getItems().add("La carta 3");
        if(player == null)
            player = (Jugador) AppContext.getInstance().get("Jugador");
        AppContext.getInstance().set("Juego", FlowController.getInstance().getController("Juego"));
        ArrayList<Jugador> jug = (ArrayList<Jugador>) AppContext.getInstance().get("Jugadores");
        jug.forEach( (j) -> {
            if(!j.getId().equals(player.getId()))
                oponentes.add(j);
        });
        lblPlayer.setText("ID: "+player.getId()+" Nick: "+player.getNick());
        lblOponente.setText("ID: "+oponentes.get(oponente).getId()+" Nick: "+oponentes.get(oponente).getNick());
        if(player.getTurno() > 1)
            hilo();
        conseguirImagenes();
        
    }

    private void conseguirImagenes(){
        mano[3].setImage(new Image("/virus/resources/Dorso.jpg"));
        for(int i =0; i < 3; i++){
            mano[i].setImage(player.getMano().get(i).getImagen());
            mano[i].setCarta(player.getMano().get(i));
        }
        /*
        
        Carta car;
        Tratamiento tra;
        for(int i =0; i < 3; i++){
            if(player.getMano().get(i).getClass().equals(Carta.class)){
                car = (Carta)player.getMano().get(i);
                mano[i].setCarta(car);
                mano[i].setImage(car.getImagen());
            }else{
                tra = (Tratamiento)player.getMano().get(i);
                mano[i].setCarta(tra);
                mano[i].setImage(tra.getImagen());
            }
        }
        
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
        System.out.println(index);*/
    }
    
    /*public String infoGridPane(){
        
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
    }*/
    private void mostrarOponente(){
        lblOponente.setText("ID: "+oponentes.get(oponente).getId()+" Nick: "+oponentes.get(oponente).getNick());
        Carta[][] tab = oponentes.get(oponente).getMatTablero();
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                tabOponente[i][j].setCarta(null);
                tabOponente[i][j].setImage(null);
                if(tab[i][j].getImagen() != null){
                    tabOponente[i][j].setCarta(tab[i][j]);
                    tabOponente[i][j].setImage(tab[i][j].getImagen());
                }
            }
        }
    }
    
    private void mostrarTablero(){
        Carta[][] tab = player.getMatTablero();
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                tablero[j][i].setCarta(null);
                tablero[j][i].setImage(null);
                if(tab[i][j].getImagen() != null){
                    tablero[j][i].setCarta(tab[i][j]);
                    tablero[j][i].setImage(tab[i][j].getImagen());
                }
            }
        }
    }
    
    @FXML
    private void accionJugada(ActionEvent event) {
        if(player.getTurno() == 1 && timer == null)
            hilo();
    }

    @FXML
    private void accionSiguente(ActionEvent event) {
        if(oponente == oponentes.size() - 1){
            oponente = 0;
        }else{
            oponente++;
        }
        mostrarOponente();
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

    @FXML
    private void accionTableroOponente(MouseEvent event) {
        //Carta de tratamiendo ladrón de organos
        if(click == 1){
            int pos = Integer.parseInt(primero.getId());
            Carta carta1 = mano[pos].getCarta();
            segundo = (ImageView) event.getSource();
            char f = segundo.getId().charAt(0);
            char c= segundo.getId().charAt(1);
            int fila = Character.getNumericValue(f);
            int columna = Character.getNumericValue(c);
            Carta carta2 = tabOponente[fila][columna].getCarta();
            if(verificarInmunidad(fila)){
                if(verificarOrganoRepetido(carta2)){
                    int disponible = 0;
                    for(int a=0;a<5;a++){
                        if(tablero[0][a].getCarta() == null){
                            disponible = a;
                            break;
                        }
                    }
                    primero.setImage(null);
                    mano[pos].setCarta(null);
                    mano[pos].setImage(null);
                    player.getMano().remove(carta1);
                    //Tranferir el organo
                    tablero[0][disponible].setCarta(carta2);
                    tablero[0][disponible].setImage(carta2.getImagen());
                    tablero[0][disponible].getImage().setImage(carta2.getImagen());
                    //Eliminar el organo del oponente
                    tabOponente[fila][columna].setCarta(null);
                    tabOponente[fila][columna].setImage(null);
                    segundo.setImage(null);
                    //Tranferir cartas que aconpañan al organo
                    //Primera
                    if(tabOponente[fila][1].getCarta() != null){
                        carta2 = tabOponente[fila][1].getCarta();
                        tablero[1][disponible].setCarta(carta2);
                        tablero[1][disponible].setImage(carta2.getImagen());
                        tablero[1][disponible].getImage().setImage(carta2.getImagen());
                        //Eliminar la carta del tablero del oponente
                        tabOponente[fila][1].setCarta(null);
                        tabOponente[fila][1].setImage(null);
                        tabOponente[fila][1].getImage().setImage(null);
                    }
                    //Segunda
                    if(tabOponente[fila][2].getCarta() != null){
                        carta2 = tabOponente[fila][2].getCarta();
                        tablero[2][disponible].setCarta(carta2);
                        tablero[2][disponible].setImage(carta2.getImagen());
                        tablero[2][disponible].getImage().setImage(carta2.getImagen());
                        //Eliminar la carta del tablero del oponente
                        tabOponente[fila][2].setCarta(null);
                        tabOponente[fila][2].setImage(null);
                        tabOponente[fila][2].getImage().setImage(null);
                    }
                    
                    player.copiarMatrizJugador(tablero);
                    oponentes.get(oponente).copiarMatrizOponente(tabOponente);
                }
            }
            
            primero = null;
            segundo = null;
            click = 0;
            
        //Carta común de virus
        }else{
            if(primero != null){
            //Coordenada de la carta seleccionada en la mano del jugador
            int pos = Integer.valueOf(primero.getId());
            Carta carta1 = mano[pos].getCarta();
            //Coordenas de la carta en el tablero del jugador
            if(carta1 != null){
                segundo = (ImageView) event.getSource();
                char f = segundo.getId().charAt(0);
                char c= segundo.getId().charAt(1);
                int fila = Character.getNumericValue(f);
                int columna = Character.getNumericValue(c);
                Carta carta2 = tabOponente[fila][columna].getCarta();
                //Funcionalidad de la carta virus
                if(columna != 0){
                    if(tabOponente[fila][0].getCarta() != null){
                        if(carta2 == null){
                            if(tablero[fila][0].getCarta().getColor() == carta1.getColor() || carta1.getColor() == 5){
                                switch(verificarEstadoOponente(fila)){
                                    //El organo esta sano
                                    case 1:{
                                        primero.setImage(null);
                                        mano[pos].setCarta(null);
                                        mano[pos].setImage(null);
                                        player.getMano().remove(carta1);
                                        tabOponente[fila][columna].setCarta(carta1);
                                        tabOponente[fila][columna].setImage(carta1.getImagen());
                                        segundo.setImage(carta1.getImagen());
                                        oponentes.get(oponente).copiarMatrizOponente(tabOponente);
                                        break;
                                    }
                                    //El organo esta enfermo
                                    case 2:{
                                        ArrayList<Carta> lista = (ArrayList<Carta>) AppContext.getInstance().get("Descartes");
                                        primero.setImage(null);
                                        mano[pos].setCarta(null);
                                        mano[pos].setImage(null);
                                        player.getMano().remove(carta1);
                                        lista.add(carta1);
                                        lista.add(tablero[fila][3-columna].getCarta());
                                        lista.add(tabOponente[fila][0].getCarta());
                                        tabOponente[fila][3-columna].setCarta(null);
                                        tabOponente[fila][3-columna].setImage(null);
                                        tabOponente[fila][0].setCarta(null);
                                        tabOponente[fila][0].setImage(null);
                                        segundo.setImage(null);
                                        oponentes.get(oponente).copiarMatrizOponente(tabOponente);
                                        break;
                                    }
                                    //El organo esta vacunado
                                    case 3:{
                                        ArrayList<Carta> lista = (ArrayList<Carta>) AppContext.getInstance().get("Descartes");
                                        primero.setImage(null);
                                        mano[pos].setCarta(null);
                                        mano[pos].setImage(null);
                                        player.getMano().remove(carta1);
                                        lista.add(carta1);
                                        lista.add(tabOponente[fila][3-columna].getCarta());
                                        tabOponente[fila][3-columna].setCarta(null);
                                        tabOponente[fila][3-columna].setImage(null);
                                        segundo.setImage(null);
                                        oponentes.get(oponente).copiarMatrizOponente(tabOponente);
                                        break;
                                    }
                                    //El organo es inmune
                                    case 4:{
                                        //Nada que hacer
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        primero = null;
        segundo = null;
    }
        
    }

    @FXML
    private void accionTableroJugador(MouseEvent event) {
        if(primero!= null){
            //Coordenada de la carta seleccionada en la mano del jugador
            int pos = Integer.valueOf(primero.getId());
            Carta carta1 = mano[pos].getCarta();
            //Coordenas de la carta en el tablero del jugador
            if(carta1 != null){
                segundo = (ImageView) event.getSource();
                char f = segundo.getId().charAt(0);
                char c= segundo.getId().charAt(1);
                int fila = Character.getNumericValue(f);
                int columna = Character.getNumericValue(c);
                Carta carta2 = tablero[fila][columna].getCarta();
                //La carta es un organo
                if(carta1.getTipo() == 1){
                    if(fila == 0){
                        if(verificarOrganoRepetido(carta1)){
                            primero.setImage(null);
                            mano[pos].setCarta(null);
                            mano[pos].setImage(null);
                            player.getMano().remove(carta1);
                            tablero[fila][columna].setCarta(carta1);
                            tablero[fila][columna].setImage(carta1.getImagen());
                            segundo.setImage(carta1.getImagen());
                            player.copiarMatrizJugador(tablero);
                        }
                    }
                //La carta es una medicina
                }else{
                    if(fila != 0){
                        if(tablero[0][columna].getCarta() != null){
                            if(carta2 == null){
                                if(tablero[0][columna].getCarta().getColor() == carta1.getColor() || carta1.getColor() == 5){
                                    switch(verificarEstadoOrgano(columna)){
                                        //El organo esta sano
                                        case 1:{
                                            primero.setImage(null);
                                            mano[pos].setCarta(null);
                                            mano[pos].setImage(null);
                                            player.getMano().remove(carta1);
                                            tablero[fila][columna].setCarta(carta1);
                                            tablero[fila][columna].setImage(carta1.getImagen());
                                            segundo.setImage(carta1.getImagen());
                                            player.copiarMatrizJugador(tablero);
                                            break;
                                        }
                                        //El organo esta enfermo
                                        case 2:{
                                            ArrayList<Carta> lista = (ArrayList<Carta>) AppContext.getInstance().get("Descartes");
                                            primero.setImage(null);
                                            mano[pos].setCarta(null);
                                            mano[pos].setImage(null);
                                            player.getMano().remove(carta1);
                                            lista.add(carta1);
                                            lista.add(tablero[3-fila][columna].getCarta());
                                            tablero[3-fila][columna].setCarta(null);
                                            tablero[3-fila][columna].setImage(null);
                                            segundo.setImage(null);
                                            player.copiarMatrizJugador(tablero);
                                            break;
                                        }
                                        //El organo esta vacunado
                                        case 3:{
                                            primero.setImage(null);
                                            mano[pos].setCarta(null);
                                            mano[pos].setImage(null);
                                            player.getMano().remove(carta1);
                                            tablero[fila][columna].setCarta(carta1);
                                            tablero[fila][columna].setImage(carta1.getImagen());
                                            segundo.setImage(carta1.getImagen());
                                            player.copiarMatrizJugador(tablero);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        primero = null;
        segundo = null;
    }

    @FXML
    private void accionManoJugador(MouseEvent event) {
        primero = (ImageView) event.getSource();
        int pos = Integer.parseInt(primero.getId());
        Carta carta1 = mano[pos].getCarta();
        if(carta1.getTipo() == 4){
            switch(carta1.getColor()){
                //Ladron de organos
                case 1:{
                    click = 1;
                    break;
                }
                //Transplante
                case 2:{
                    
                    break;
                }
                //Infección
                case 3:{
                    
                    break;
                }
                //Guante de latex
                case 4:{
                    
                    break;
                }
                //Error médico
                case 5:{
                    
                    break;
                }
            }
        }
    }

    @FXML
    private void accionMazos(MouseEvent event) {
        
    }
    
    private boolean verificarOrganoRepetido(Carta organo){
        if(organo == null){
            return false;
        }
        if(organo.getColor() == 5){
            return true;
        }
        for(int a=0;a<5;a++){
            if(tablero[0][a].getCarta() != null){
                if(tablero[a][0].getCarta().getColor() == organo.getColor()){
                    return false;
                }
            }
        }
        return true;
    }
    
    private int verificarEstadoOrgano(int columna){
        //El organo esta sano
        if(tablero[1][columna].getCarta() == null && tablero[2][columna].getCarta() == null){
            return 1;
        //El organo esta enfermo
        }
        if(tablero[1][columna].getCarta().getTipo() == 3 || tablero[2][columna].getCarta().getTipo() == 3){
            return 2;
        }
        //El organo esta vacunado
        if(tablero[1][columna].getCarta().getTipo() == 2 || tablero[2][columna].getCarta().getTipo() == 2){
            return 3;
        }
        return 0;
    }
    
    private int verificarEstadoOponente(int fila){
        //El organo esta imnune
        if(tabOponente[fila][1].getCarta().getTipo() == 2 && tabOponente[fila][2].getCarta().getTipo() == 2){
            return 4;
        }
        //El organo esta sano
        if(tabOponente[fila][1].getCarta() == null && tabOponente[fila][2].getCarta() == null){
            return 1;
        //El organo esta enfermo
        }
        if(tabOponente[fila][1].getCarta().getTipo() == 3 || tabOponente[fila][2].getCarta().getTipo() == 3){
            return 2;
        }
        //El organo esta vacunado
        if(tabOponente[fila][1].getCarta().getTipo() == 2 || tabOponente[fila][2].getCarta().getTipo() == 2){
            return 3;
        }
        return 0;
    }
    
    public boolean verificarInmunidad(int fila){
        boolean pos1 = false;
        boolean pos2 = false;
        
        if(tabOponente[fila][1].getCarta() != null){
            if(tabOponente[fila][1].getCarta().getTipo() != 2){
                pos1 = true;
            }
        }else{
            pos1 = false;
        }
        
        if(tabOponente[fila][2].getCarta() != null){
            if(tabOponente[fila][2].getCarta().getTipo() != 2){
                pos2 = true;
            }
        }else{
            pos2 = false;
        }
        //El organo esta inmune
        if(pos1 && pos2){
            return false;
        }
        return true;
    }
    
     public void hilo(){
        timer = new Timer();
        AppContext.getInstance().set("Timer", timer);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("HILO");
                con.accionEnviar("4", "");
            }
        }, 10000, 10000);
    }
}
