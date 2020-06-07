package virus.util;

public class Virus {
    private String color;
    
    Virus(){
        this.color = "";
    }
    
    Virus(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
