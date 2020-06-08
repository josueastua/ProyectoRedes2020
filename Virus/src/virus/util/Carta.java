package virus.util;

public class Carta {
    private int tipo;
    private int color;
    
    Carta(){
        
    }
    
    Carta(int tipo, int color){
        this.tipo = tipo;
        this.color = color;
    }

    public int getTipo() {
        return tipo;
    }

    public Integer getColor() {
        return color;
    }
    
}
