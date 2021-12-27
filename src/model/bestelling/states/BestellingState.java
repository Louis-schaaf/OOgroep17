package model.bestelling.states;

public abstract class BestellingState {
    void starten() {}
    void afronden() {}
    void betalen() {}
    void verzenden() {}
    void bereiden(){}
    void afwerken(){}
    void annuleren(){}
    void wachten(){}
}
