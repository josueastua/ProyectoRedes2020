package virus.util;

public class Tratamiento {
    private String tipo;
    
    Tratamiento(){
        this.tipo = "";
    }

    Tratamiento(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
    
}
