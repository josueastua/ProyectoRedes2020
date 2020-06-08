package virus.util;

public class Organo {
    private String color;
    private boolean vacunado;
    private boolean inmune;
    private boolean enfermo;
    private Medicina medicina;
    private Virus virus;
    
    Organo(){
        this.color = "";
        this.vacunado = false;
        this.inmune = false;
        this.enfermo = false;
        this.medicina = null;
        this.virus = null;
    }
    
    Organo(String color){
        this.color = color;
        this.vacunado = false;
        this.inmune = false;
        this.enfermo = false;
        this.medicina = null;
        this.virus = null;
    }
    
    public boolean isVacunado() {
        return vacunado;
    }

    public boolean isInmune() {
        return inmune;
    }

    public boolean isEnfermo() {
        return enfermo;
    }

    public String getColor() {
        return color;
    }

    public Medicina getMedicina() {
        return medicina;
    }

    public Virus getVirus() {
        return virus;
    }
}
