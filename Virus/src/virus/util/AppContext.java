package virus.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import javafx.scene.image.Image;


public class AppContext {

    private static AppContext INSTANCE = null;
    private static HashMap<String, Object> context = new HashMap<>();
    private static HashMap<String, Image> cartas = new HashMap<>();
     
    private AppContext() {
        cargarCartas();
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (AppContext.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppContext();
                }
            }
        }
    }

    public static AppContext getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    private void cargarCartas(){
        cartas.put("1:0:1", new Image("virus/resources/o1.png"));
        cartas.put("1:0:2", new Image("virus/resources/o2.png"));
        cartas.put("1:0:3", new Image("virus/resources/o4.png"));
        cartas.put("1:0:3", new Image("virus/resources/o4.png"));
        cartas.put("1:0:5", new Image("virus/resources/o5.png"));
        
        cartas.put("2:4:1", new Image("virus/resources/m13.png"));
        cartas.put("2:2:2", new Image("virus/resources/m23.png"));
        cartas.put("2:3:3", new Image("virus/resources/m31.png"));
        cartas.put("2:1:4", new Image("virus/resources/m41.png"));
        cartas.put("2:5:5", new Image("virus/resources/m51.png"));
        
        cartas.put("3:2:1", new Image("virus/resources/v12.png"));
        cartas.put("3:3:2", new Image("virus/resources/v22.png"));
        cartas.put("3:1:3", new Image("virus/resources/v32.png"));
        cartas.put("2:3:3", new Image("virus/resources/m31.png"));
        cartas.put("3:4:4", new Image("virus/resources/v43.png"));
        cartas.put("3:5:5", new Image("virus/resources/v51.png"));
        
        cartas.put("4:0:1", new Image("virus/resources/t1.png"));
        cartas.put("4:0:2", new Image("virus/resources/t2.png"));
        cartas.put("4:0:3", new Image("virus/resources/t3.png"));
        cartas.put("4:0:4", new Image("virus/resources/t4.png"));
        cartas.put("4:0:5", new Image("virus/resources/t5.png"));
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public Object get(String parameter){    
        return context.get(parameter);
    }

    public void set(String nombre, Object valor) {
        context.put(nombre, valor);
    }

    public void delete(String parameter) {
        context.put(parameter, null);
    }

    public void clear(){
        context.clear();
    }
}
