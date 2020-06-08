/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus;

import javafx.application.Application;
import javafx.stage.Stage;
import virus.util.FlowController;

/**
 *
 * @author IVAN
 */
public class Virus extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FlowController.getInstance().goViewInResizableWindow("Inicio", 0, 600, 0, 500, Boolean.TRUE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
