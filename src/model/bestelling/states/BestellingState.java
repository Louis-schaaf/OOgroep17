package model.bestelling.states;

public abstract class BestellingState {
    public void starten() {}
    public void afronden() {}
    public void betalen() {}
    public void verzenden() {}
    public void bereiden(){}
    public void afwerken(){}
    public void annuleren(){}
    public void wachten(){}
}
