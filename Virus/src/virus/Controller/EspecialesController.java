/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import virus.util.AppContext;
import virus.util.Carta;
import virus.util.Cuerpo;
import virus.util.Jugador;
import virus.util.Mensaje;

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
    @FXML
    private GridPane gp1;
    @FXML
    private GridPane gp2;
    
    Cuerpo matPrimero[][] = new Cuerpo[5][3];
    Cuerpo matSegundo[][] = new Cuerpo[5][3];
    ArrayList<Jugador> jugadores;
    ImageView primero;
    ImageView segundo;
    
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
        gp1.setMouseTransparent(true);
        gp2.setMouseTransparent(true);
    }
    
    public void cargarImagenes(int jug){
        if(jug == 1){
            Jugador jugador1 = cbJugador1.getSelectionModel().getSelectedItem();
            for(int a=0;a<5;a++){
                for(int b=0;b<3;b++){
                    if(jugador1.getMatTablero()[a][b] != null){
                        matPrimero[a][b].setCarta(jugador1.getMatTablero()[a][b]);
                        matPrimero[a][b].setImage(jugador1.getMatTablero()[a][b].getImagen());
                    }else{
                        matPrimero[a][b].setImage(null);
                        matPrimero[a][b].setCarta(null);
                    }
                    
                }
            }
        }else{
            Jugador jugador2 = cbJugador2.getSelectionModel().getSelectedItem();
            for(int a=0;a<5;a++){
                for(int b=0;b<3;b++){
                    if(jugador2.getMatTablero()[a][b] != null){
                        matSegundo[a][b].setCarta(jugador2.getMatTablero()[a][b]);
                        matSegundo[a][b].setImage(jugador2.getMatTablero()[a][b].getImagen());
                    }else{
                        matSegundo[a][b].setImage(null);
                        matSegundo[a][b].setCarta(null);
                    }
                    
                }
            }
        }
    }
    
    public boolean verificarOrganorepetido(int jug, int color){
        if(jug == 1){
            for(int a=0;a<5;a++){
                if(matPrimero[a][0]!= null){
                    if(matPrimero[a][0].getCarta().getColor() == color){
                        System.out.println("Jugador 1");
                        return false;
                    }
                }
            }
            return true;
        }else{
            for(int a=0;a<5;a++){
                if(matSegundo[a][0]!= null){
                    if(matSegundo[a][0].getCarta().getColor() == color){
                        System.out.println("Jugador 2");
                        return false;
                    }
                }
            }
            return true;
        }
    }
    
    public boolean verificarInmunidad(int jug, int fila){
        if(jug == 1){
            if(matPrimero[fila][1].getCarta() != null && matPrimero[fila][2].getCarta() != null){
                if(matPrimero[fila][1].getCarta().getTipo() == 2 && matPrimero[fila][2].getCarta().getTipo() == 2){
                    System.out.println("Jugador 1");
                    return false;
                }
            }
            return true;
        }else{
           if(matSegundo[fila][1].getCarta() != null && matSegundo[fila][2].getCarta() != null){
                if(matSegundo[fila][1].getCarta().getTipo() == 2 && matSegundo[fila][2].getCarta().getTipo() == 2){
                    System.out.println("Jugador 2");
                    return false;
                }
            }
           return true;
        }
    }
    
    @FXML
    private void ElegirJugador(ActionEvent event) {
        if(cbJugador1.getSelectionModel().getSelectedItem() != null && cbJugador2.getSelectionModel().getSelectedItem() != null){
            if(cbJugador1.getSelectionModel().getSelectedItem() != cbJugador2.getSelectionModel().getSelectedItem()){
                cargarImagenes(1);
                cargarImagenes(2);
                Mensaje men = new Mensaje();
                if(men.showConfirmation("Transplante de organos", this.getStage(), "¿Desea elegir estos jugadores para al transplante?")){
                    cbJugador1.setMouseTransparent(true);
                    cbJugador2.setMouseTransparent(true);
                    gp1.setMouseTransparent(false);
                    gp2.setMouseTransparent(false);
                }
            }
        }else if(cbJugador1.getSelectionModel().getSelectedItem() != null){
            cargarImagenes(1);
        }else if(cbJugador2.getSelectionModel().getSelectedItem() != null){
            cargarImagenes(2);
        }
    }
    
    @FXML
    private void organoMat1(MouseEvent event) {
        if(segundo == null){
            primero = (ImageView) event.getSource();
        }else{
            //Coordenadas primera matriz
            primero = (ImageView) event.getSource();
            char f1 = primero.getId().charAt(0);
            char c1 = primero.getId().charAt(1);
            int fila1 = Character.getNumericValue(f1);
            int columna1 = Character.getNumericValue(c1);
            //Coordenadas segunda matriz
            char f2 = segundo.getId().charAt(0);
            char c2 = segundo.getId().charAt(1);
            int fila2 = Character.getNumericValue(f2);
            int columna2 = Character.getNumericValue(c2);
            //Validar que las cartas sean organos
            if(columna1 == 0 && columna2 == 0){
                Carta carta1 = matPrimero[fila1][columna1].getCarta();
                Carta carta2 = matSegundo[fila2][columna2].getCarta();
                if(verificarOrganorepetido(1, carta1.getColor()) && verificarOrganorepetido(2, carta2.getColor())){
                    if(verificarInmunidad(1, fila1)  && verificarInmunidad(2, fila2)){
                        //Transplantar los organos
                        //Copiar el organo del primer jugador para transplantarlo
                        Cuerpo aux1[] = new Cuerpo[3];
                        aux1[0] = matPrimero[fila1][0];
                        aux1[1] = matPrimero[fila1][1];
                        aux1[2] = matPrimero[fila1][2];
                        //Copiar el organo del segundo jugador para transplantarlo
                        Cuerpo aux2[] = new Cuerpo[3];
                        aux2[0] = matSegundo[fila2][0];
                        aux2[1] = matSegundo[fila2][1];
                        aux2[2] = matSegundo[fila2][2];
                        //Transplantar el primero
                        matPrimero[fila1][0] = aux2[0];
                        matPrimero[fila1][1] = aux2[1];
                        matPrimero[fila1][2] = aux2[2];
                        //Transplantar el segundo
                        matSegundo[fila2][0] = aux1[0];
                        matSegundo[fila2][1] = aux1[1];
                        matSegundo[fila2][2] = aux1[2];
                        //Reflejar cambios
                        cbJugador1.getSelectionModel().getSelectedItem().copiarMatrizOponente(matPrimero);
                        cbJugador2.getSelectionModel().getSelectedItem().copiarMatrizOponente(matSegundo);
                        this.getStage().close();
                    }else{
                        System.out.println("Organo inmune");
                    }
                }else{
                    System.out.println("Organo repetido");
                }
            }else{
                System.out.println("No se seleccionarón 2 organos para el transplante");
            }
            primero = null;
            segundo = null;
        }
            
    }

    @FXML
    private void organoMat2(MouseEvent event) {
        if(primero == null){
            segundo = (ImageView) event.getSource();
        }else{
            //Coordenadas primera matriz
            segundo = (ImageView) event.getSource();
            char f1 = primero.getId().charAt(0);
            char c1 = primero.getId().charAt(1);
            int fila1 = Character.getNumericValue(f1);
            int columna1 = Character.getNumericValue(c1);
            //Coordenadas segunda matriz
            char f2 = segundo.getId().charAt(0);
            char c2 = segundo.getId().charAt(1);
            int fila2 = Character.getNumericValue(f2);
            int columna2 = Character.getNumericValue(c2);
            //Validar que las cartas sean organos
            if(columna1 == 0 && columna2 == 0){
                Carta carta1 = matPrimero[fila1][columna1].getCarta();
                Carta carta2 = matSegundo[fila2][columna2].getCarta();
                if(verificarOrganorepetido(1, carta1.getColor()) && verificarOrganorepetido(2, carta2.getColor())){
                    if(verificarInmunidad(1, fila1)  && verificarInmunidad(2, fila2)){
                        //Transplantar los organos
                        //Copiar el organo del primer jugador para transplantarlo
                        Cuerpo aux1[] = new Cuerpo[3];
                        aux1[0] = matPrimero[fila1][0];
                        aux1[1] = matPrimero[fila1][1];
                        aux1[2] = matPrimero[fila1][2];
                        //Copiar el organo del segundo jugador para transplantarlo
                        Cuerpo aux2[] = new Cuerpo[3];
                        aux2[0] = matSegundo[fila2][0];
                        aux2[1] = matSegundo[fila2][1];
                        aux2[2] = matSegundo[fila2][2];
                        //Transplantar el primero
                        matPrimero[fila1][0] = aux2[0];
                        matPrimero[fila1][1] = aux2[1];
                        matPrimero[fila1][2] = aux2[2];
                        //Transplantar el segundo
                        matSegundo[fila2][0] = aux1[0];
                        matSegundo[fila2][1] = aux1[1];
                        matSegundo[fila2][2] = aux1[2];
                        //Reflejar cambios
                        cbJugador1.getSelectionModel().getSelectedItem().copiarMatrizOponente(matPrimero);
                        cbJugador2.getSelectionModel().getSelectedItem().copiarMatrizOponente(matSegundo);
                        AppContext.getInstance().set("Transplante", true);
                        this.getStage().close();
                    }else{
                        System.out.println("Organo inmune");
                    }
                }else{
                    System.out.println("Organo repetido");
                }
            }else{
                System.out.println("No se seleccionarón 2 organos para el transplante");
            }
            primero = null;
            segundo = null;
        }
    }
    
    @Override
    public void initialize() {
        AppContext.getInstance().set("Transplante", false);
    }
}
