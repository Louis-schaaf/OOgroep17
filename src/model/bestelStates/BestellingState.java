package model.bestelStates;

import model.Bestelling;

public abstract class BestellingState {
    protected Bestelling bestelling;

    public BestellingState(Bestelling bestelling){
        this.bestelling = bestelling;
    }

    public abstract void starten();
    public abstract void afronden();
    public abstract void betalen();
    public abstract void verzenden();
    public abstract void bereiden();
    public abstract void afwerken();
    public abstract void annuleren();
}
