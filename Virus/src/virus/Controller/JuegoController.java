/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.layout.StackPane;
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
    ArrayList<Carta> mazo = new ArrayList<>();
    ArrayList<Object> mazo_img = new ArrayList<>();
    ArrayList<Carta> descartes = new ArrayList<>();
    ArrayList<Object> descartes_img = new ArrayList<>();
    Jugador player = null;
    int turno = 1;
    int oponente = 0;
    ImageView primero = null;
    ImageView segundo = null;
    int click = 0;
    boolean jugada = false;
    
    
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
    @FXML
    private StackPane stack;
    Boolean consultar = false;
    
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
                vb_player.setPrefWidth(root.getWidth());
            }
        });
        
    }
    
    public void actualizarJuego(){
        Platform.runLater( () -> {
            System.out.println("Entro");
            mazo =  (ArrayList<Carta>) AppContext.getInstance().get("Mazo");
            descartes = (ArrayList<Carta>) AppContext.getInstance().get("Descartes");
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
        mazo =  (ArrayList<Carta>) AppContext.getInstance().get("Mazo");
        if(AppContext.getInstance().get("Descartes") != null){
            descartes = (ArrayList<Carta>) AppContext.getInstance().get("Descartes");
        }
        conseguirImagenes();
        mostrarOponente();
        if(player.getTurno() != 1)
            men.show(Alert.AlertType.INFORMATION, "Jugar", "Aun no es su turno");
    }

    private void conseguirImagenes(){
        mano[3].setImage(new Image("/virus/resources/Dorso.jpg"));
        for(int i =0; i < 3; i++){
            mano[i].setImage(player.getMano().get(i).getImagen());
            mano[i].setCarta(player.getMano().get(i));
        }
    }
    
    private void mostrarOponente(){
        lblOponente.setText("ID: "+oponentes.get(oponente).getId()+" Nick: "+oponentes.get(oponente).getNick());
        Carta[][] tab = oponentes.get(oponente).getMatTablero();
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                tabOponente[i][j].setCarta(null);
                tabOponente[i][j].setImage(null);
                if(tab[i][j] != null){
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
                if(tab[i][j] != null){
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
        System.out.println(hacerJugada());
        con.accionEnviar("3", hacerJugada());
        consultar = true;
        cbDescartar.setMouseTransparent(false);
        jugada = false;
    }
    
    private String hacerJugada(){
        List<Jugador> jugadores = (List<Jugador>) AppContext.getInstance().get("Jugadores");
        String men = "";
        for(Jugador jug: jugadores){
            men += jug.infoJugador()+"#";
        }
        men = men.substring(0, men.length() - 1);
        men += "/";
        for(Carta carta: mazo){
            men += carta.getRepresentacion()+"-";
        }
        men = men.substring(0, men.length() - 1);
        men += "/";
        if(descartes != null){
            if(!descartes.isEmpty()){
                for(Carta carta: descartes){
                    System.out.println(carta.toString() + descartes.indexOf(carta));
                    men += carta.getRepresentacion()+"-";
                }
                men = men.substring(0, men.length() - 1);
            }else{
                men += "0";
            }
        }else{
            men += "0";
        }
        men += "/0-0-0";
        return men;
    }
    
    public void comerCarta(){
        //Se encarga de reponer la carta faltante de la mano luego de una jugada
        if(jugada){
            verificarCantidadMazo();
            player.addMano(mazo.get(0));
            mazo.remove(0);
            conseguirImagenes();
        //Rellena la mano del jugador luego de botar cartas
        }else{
            int cantidad = 3 - player.getMano().size();
            for(int a=0;a<cantidad;a++){
                verificarCantidadMazo();
                player.addMano(mazo.get(0));
                mazo.remove(0);
            }
            conseguirImagenes();
        }
    }
    
    public void verificarCantidadMazo(){
        if(mazo.isEmpty()){
            mazo.addAll(descartes);
            descartes.clear();
        }
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
                   //jugada = true;
                    int disponible = 0;
                    for(int a=0;a<5;a++){
                        if(tablero[0][a].getCarta() == null){
                            disponible = a;
                            break;
                        }
                    }
                    jugada = true;
                    primero.setImage(null);
                    mano[pos].setCarta(null);
                    mano[pos].setImage(null);
                    player.getMano().remove(carta1);
                    //Obtener una nueva carta del mazo
                    comerCarta();
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
                if(carta2!=null){
                    System.out.println("Carta "+carta2.getTipo()+":"+carta2.getColor());
                }else{
                    System.out.println("NULO");
                }
                //Funcionalidad de la carta virus
                if(columna != 0){
                    if(tabOponente[fila][0].getCarta() != null){
                        if(carta2 == null){
                            if(tabOponente[fila][0].getCarta().getColor() == carta1.getColor() || tabOponente[fila][0].getCarta().getColor() == 5 || carta1.getColor() == 5){
                                if(carta1.getTipo() == 3){
                                    switch(verificarEstadoOponente(fila)){
                                        //El organo esta sano
                                        case 1:{
                                            jugada = true;
                                            primero.setImage(null);
                                            mano[pos].setCarta(null);
                                            mano[pos].setImage(null);
                                            player.getMano().remove(carta1);
                                            tabOponente[fila][columna].setCarta(carta1);
                                            tabOponente[fila][columna].setImage(carta1.getImagen());
                                            segundo.setImage(carta1.getImagen());
                                            oponentes.get(oponente).copiarMatrizOponente(tabOponente);
                                            //Tomar una nueva carta
                                            comerCarta();
                                            break;
                                        }
                                        //El organo esta enfermo
                                        case 2:{
                                            jugada = true;
                                            primero.setImage(null);
                                            mano[pos].setCarta(null);
                                            mano[pos].setImage(null);
                                            descartes.add(carta1);
                                            descartes.add(tablero[fila][3-columna].getCarta());
                                            descartes.add(tabOponente[fila][0].getCarta());
                                            tabOponente[fila][3-columna].setCarta(null);
                                            tabOponente[fila][3-columna].setImage(null);
                                            tabOponente[fila][0].setCarta(null);
                                            tabOponente[fila][0].setImage(null);
                                            segundo.setImage(null);
                                            player.getMano().remove(carta1);
                                            oponentes.get(oponente).copiarMatrizOponente(tabOponente);
                                            //Tomar una nueva carta
                                            comerCarta();
                                            break;
                                        }
                                        //El organo esta vacunado
                                        case 3:{
                                            jugada = true;
                                            primero.setImage(null);
                                            mano[pos].setCarta(null);
                                            mano[pos].setImage(null);
                                            descartes.add(carta1);
                                            descartes.add(tabOponente[fila][3-columna].getCarta());
                                            tabOponente[fila][3-columna].setCarta(null);
                                            tabOponente[fila][3-columna].setImage(null);
                                            segundo.setImage(null);
                                            player.getMano().remove(carta1);
                                            
                                            oponentes.get(oponente).copiarMatrizOponente(tabOponente);
                                            //Tomar una nueva carta
                                            comerCarta();
                                            break;
                                        }
                                        //El organo es inmune
                                        case 4:{
                                            //Nada que hacer
                                            System.out.println("ES INMUNE");
                                            break;
                                        }
                                    }
                                }
                            }else{
                                System.out.println("Color distinto");
                            }
                        }
                    }else{
                        System.out.println("No hay organo para infectar");
                    }
                }else{
                    System.out.println("Columna incorrecta");
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
                if(carta2 != null){
                    System.out.println("Carta 2 "+carta2.getTipo()+":"+carta2.getColor());
                }else{
                    System.out.println("Nulo");
                }
                
                //La carta es un organo
                if(carta1.getTipo() == 1){
                    System.out.println("Organo");
                    if(fila == 0){
                        if(verificarOrganoRepetido(carta1)){
                            if(carta2 == null){
                               jugada = true;
                                primero.setImage(null);
                                mano[pos].setCarta(null);
                                mano[pos].setImage(null);
                                player.getMano().remove(carta1);
                                tablero[fila][columna].setCarta(carta1);
                                tablero[fila][columna].setImage(carta1.getImagen());
                                segundo.setImage(carta1.getImagen());
                                player.copiarMatrizJugador(tablero);
                                //Tomar una nueva carta
                                comerCarta();
                                }else{
                                System.out.println("La posición ya esta ocupada");
                            }
                        }else{
                            System.out.println("Organo repetido");
                        }
                    }else{
                        System.out.println("Fila incorrecta");
                    }
                //La carta es una medicina
                }else if(carta1.getTipo() == 2){ 
                    System.out.println("Medicina");
                    if(fila != 0){
                        if(tablero[0][columna].getCarta() != null){
                            if(carta2 == null){
                                if(tablero[0][columna].getCarta().getColor() == carta1.getColor() || carta1.getColor() == 5 || tablero[0][columna].getCarta().getColor() == 5){
                                    switch(verificarEstadoOrgano(columna)){
                                        //El organo esta sano
                                        case 1:{
                                            jugada = true;
                                            primero.setImage(null);
                                            mano[pos].setCarta(null);
                                            mano[pos].setImage(null);
                                            player.getMano().remove(carta1);
                                            tablero[fila][columna].setCarta(carta1);
                                            tablero[fila][columna].setImage(carta1.getImagen());
                                            segundo.setImage(carta1.getImagen());
                                            player.copiarMatrizJugador(tablero);
                                            //Tomar una nueva carta
                                            comerCarta();
                                            break;
                                        }
                                        //El organo esta enfermo
                                        case 2:{
                                            jugada = true;
                                            primero.setImage(null);
                                            mano[pos].setCarta(null);
                                            mano[pos].setImage(null);
                                            player.getMano().remove(carta1);
                                            descartes.add(carta1);
                                            descartes.add(tablero[3-fila][columna].getCarta());
                                            tablero[3-fila][columna].setCarta(null);
                                            tablero[3-fila][columna].setImage(null);
                                            segundo.setImage(null);
                                            player.copiarMatrizJugador(tablero);
                                            //Tomar una nueva carta
                                            comerCarta();
                                            break;
                                        }
                                        //El organo esta vacunado
                                        case 3:{
                                            jugada = true;
                                            primero.setImage(null);
                                            mano[pos].setCarta(null);
                                            mano[pos].setImage(null);
                                            player.getMano().remove(carta1);
                                            tablero[fila][columna].setCarta(carta1);
                                            tablero[fila][columna].setImage(carta1.getImagen());
                                            segundo.setImage(carta1.getImagen());
                                            player.copiarMatrizJugador(tablero);
                                            //Tomar una nueva carta
                                            comerCarta();
                                            break;
                                        }
                                    }
                                }else{
                                    System.out.println("El color del organo es distinto");
                                    System.out.println("Color 1 = "+ carta1.getColor() + " Color 2 = " + tablero[0][columna].getCarta().getColor());
                                }
                            }else{
                                System.out.println("El espacio no esta disponible");
                            }
                        }else{
                            System.out.println("No hay un organo al cual vacunar");
                        }
                    }else{
                        System.out.println("Posición incorrecta");
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
        System.out.println("Carta "+carta1.getTipo()+":"+carta1.getColor());
        if(carta1.getTipo() == 4){
            switch(carta1.getColor()){
                //Ladron de organos
                case 1:{
                    Mensaje mensaje = new Mensaje();
                    if(mensaje.showConfirmation("Carta ladrón de organos", this.getStage(), "¿Desea usar esta carta?")){
                        click = 1;
                    }
                    break;
                }
                //Transplante
                case 2:{
                    Mensaje mensaje = new Mensaje();
                    if(mensaje.showConfirmation("Carta transplante de organos", this.getStage(), "¿Desea usar esta carta?")){
                        FlowController.getInstance().goViewInNoResizableWindow("Especiales", false);
                        mostrarOponente();
                        mostrarTablero();
                        if((boolean)AppContext.getInstance().get("Transplante")){
                            System.out.println("Transplante exitoso");
                            mano[pos].setCarta(null);
                            mano[pos].setImage(null);
                            primero.setImage(null);
                            jugada = true;
                            comerCarta();
                            AppContext.getInstance().set("Transplante", false);
                         }
                    }
                    
                    break;
                }
                //Infección
                case 3:{
                    //Vector para guardar el color de las cartas que pueden ser infectadas
                    int vecColor[] = new int[5];
                    Mensaje mensaje = new Mensaje();
                    if(mensaje.showConfirmation("Carta infección", this.getStage(), "¿Desea usar esta carta?")){
                        for(int a=0;a<5;a++){
                            if(tabOponente[a][0].getCarta() != null){
                                if(verificarEstadoOponente(a) == 1){
                                    vecColor[a] = tabOponente[a][0].getCarta().getColor();
                                }else{
                                    vecColor[a] = -1;
                                }
                            }else{
                                vecColor[a] = 0;
                            }
                        }
                        jugada = true;
                        mano[pos].setCarta(null);
                        mano[pos].setImage(null);
                        primero.setImage(null);
                        comerCarta();
                        //Verificar la segunda fila del jugador para ver si algún virus se puede transmitir
                        Carta aux;
                        for(int b=0;b<5;b++){
                            for(int c=0;c<5;c++){
                                if(vecColor[b] != 0 && vecColor[b] != -1){
                                aux = tablero[1][c].getCarta();
                                if(aux != null){
                                    if(aux.getTipo() == 3){
                                            if(aux.getColor() == vecColor[b] || aux.getColor() == 5 || vecColor[b] == 5){
                                                tabOponente[b][1].setCarta(aux);
                                                tabOponente[b][1].setImage(aux.getImagen());
                                                tabOponente[b][1].getImage().setImage(aux.getImagen());
                                                tablero[1][c].setCarta(null);
                                                tablero[1][c].setImage(null);
                                                tablero[1][c].getImage().setImage(null);
                                                vecColor[b] = -1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        //Verificar la tercera fila del jugador para ver si algún virus se puede transmitir
                        for(int b=0;b<5;b++){
                            for(int c=0;c<5;c++){
                                if(vecColor[b] != 0 && vecColor[b] != -1){
                                aux = tablero[2][c].getCarta();
                                if(aux != null){
                                    if(aux.getTipo() == 3){
                                            if(aux.getColor() == vecColor[b] || aux.getColor() == 5 || vecColor[b] == 5){
                                                tabOponente[b][2].setCarta(aux);
                                                tabOponente[b][2].setImage(aux.getImagen());
                                                tabOponente[b][2].getImage().setImage(aux.getImagen());
                                                tablero[2][c].setCarta(null);
                                                tablero[2][c].setImage(null);
                                                tablero[2][c].getImage().setImage(null);
                                                vecColor[b] = -1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    primero = null;
                    segundo = null;
                    break;
                }
                //Guante de latex
                case 4:{
                    Mensaje mensaje = new Mensaje();
                    if(mensaje.showConfirmation("Carta guante de látex", this.getStage(), "¿Desea usar esta carta?")){
                        for(Jugador oponente : oponentes){
                            descartes.addAll(oponente.getMano());
                            oponente.getMano().clear();
                            for(int a=0;a<3;a++){
                                verificarCantidadMazo();
                                oponente.addMano(mazo.get(0));
                                mazo.remove(0);
                            }
                        }
                        jugada = true;
                        mano[pos].setCarta(null);
                        mano[pos].setImage(null);
                        primero.setImage(null);
                        comerCarta();
                    }
                    primero = null;
                    segundo = null;
                    break;
                }
                //Error médico
                case 5:{
                    Mensaje mensaje = new Mensaje();
                    if(mensaje.showConfirmation("Carta error médico", this.getStage(), "¿Desea usar esta carta?")){
                        oponentes.get(oponente).copiarMatrizJugador(tablero);
                        player.copiarMatrizOponente(tabOponente);
                        mostrarOponente();
                        mostrarTablero();
                        primero = null;
                        segundo = null;
                        jugada = true;
                        mano[pos].setCarta(null);
                        mano[pos].setImage(null);
                        primero.setImage(null);
                        comerCarta();
                    }
                    break;
                }
            }
        }
    }

    @FXML
    private void accionMazos(MouseEvent event) {
        
    }
    
    private boolean verificarOrganoRepetido(Carta organo){
        if(organo.getColor() == 5){
            return true;
        }
        for(int a=0;a<5;a++){
            if(tablero[0][a].getCarta() != null){
                if(tablero[0][a].getCarta().getColor() == organo.getColor()){
                    System.out.println(a+"- "+tablero[0][a].getCarta().getColor());
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
        }
        //El organo esta enfermo
        if(tablero[1][columna].getCarta()!= null){
            if(tablero[1][columna].getCarta().getTipo() == 3){
                return 2;
            }
        }
        if(tablero[2][columna].getCarta()!= null){
            if(tablero[2][columna].getCarta().getTipo() == 3){
                return 2;
            }
        }
        //El organo esta vacunado
        if(tablero[1][columna].getCarta() != null){
            if(tablero[1][columna].getCarta().getTipo()== 2){
                return 3;
            }
        }
        if(tablero[2][columna].getCarta() != null){
            if(tablero[2][columna].getCarta().getTipo()== 2){
                return 3;
            }
        }
        return 0;
    }
    
    private int verificarEstadoOponente(int fila){
        //El organo esta imnune
        if(tabOponente[fila][1].getCarta() != null && tabOponente[fila][2].getCarta() != null){
            if(tabOponente[fila][1].getCarta().getTipo() == 2 && tabOponente[fila][2].getCarta().getTipo() == 2){
            return 4;
            }
        }
        //El organo esta sano
        if(tabOponente[fila][1].getCarta() == null && tabOponente[fila][2].getCarta() == null){
            return 1;
        //El organo esta enfermo
        }
        if(tabOponente[fila][1].getCarta() != null){
            if(tabOponente[fila][1].getCarta().getTipo() == 3){
                return 2;
            }
        }
        if(tabOponente[fila][2].getCarta() != null){
            if(tabOponente[fila][2].getCarta().getTipo() == 3){
                return 2;
            }
        }
        //El organo esta vacunado
        if(tabOponente[fila][1].getCarta() != null){
            if(tabOponente[fila][1].getCarta().getTipo() == 2){
                return 3;
            }
        }
        if(tabOponente[fila][2].getCarta() != null){
            if(tabOponente[fila][2].getCarta().getTipo() == 2){
                return 3;
            }
        }
        return 0;
    }
    
    public boolean verificarInmunidad(int fila){
        boolean pos1 = false;
        boolean pos2 = false;
        
        if(tabOponente[fila][1].getCarta() != null){
            if(tabOponente[fila][1].getCarta().getTipo() == 2){
                pos1 = true;
                System.out.println("Carta 1 medicina");
                System.out.println(tabOponente[fila][1].getCarta().getTipo()+":"+tabOponente[fila][1].getCarta().getColor());
            }
        }
        
        if(tabOponente[fila][2].getCarta() != null){
            if(tabOponente[fila][2].getCarta().getTipo() == 2){
                pos2 = true;
                System.out.println("Carta 2 medicina");
                System.out.println(tabOponente[fila][1].getCarta().getTipo()+":"+tabOponente[fila][1].getCarta().getColor());
            }
        }
        //El organo esta inmune
        if(pos1 && pos2){
            System.out.println("Inmune");
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
                if(consultar)
                    con.accionEnviar("4", "HOLA");
            }
        }, 5000, 5000);
    }

    @FXML
    private void accionDescartar(ActionEvent event) {
        if(cbDescartar.getSelectionModel().getSelectedItem() != null){
            tramitarPeticion(cbDescartar.getSelectionModel().getSelectedItem());
            cbDescartar.setMouseTransparent(true);
            comerCarta();
        }
    }
    
    public void tramitarPeticion(String peticion){
        int index = cbDescartar.getItems().indexOf(peticion);
        switch(index){
            case 0:
                player.getMano().clear();
                break;
            case 2:
                player.getMano().remove(0);
                player.getMano().remove(0);
                break;
            case 3:
                player.getMano().remove(0);
                player.getMano().remove(1);
                break;
            case 4:
                player.getMano().remove(1);
                player.getMano().remove(1);
                break;
            case 5:
                player.getMano().remove(0);
                break;
            case 6:
                player.getMano().remove(1);
                break;
            case 7:
                player.getMano().remove(2);
                break;
        }
    }
    
    public void esTurno(){
        Platform.runLater(() -> {
            jugada = false;
            int t = (int) AppContext.getInstance().get("Turno");
            root.setMouseTransparent((player.getTurno() != t));
            consultar = (player.getTurno() != t);
            if(!consultar){
                men.show(Alert.AlertType.INFORMATION, "Informacion de turno", "Ya puedes jugar");
            }
        });
        
    }
}
