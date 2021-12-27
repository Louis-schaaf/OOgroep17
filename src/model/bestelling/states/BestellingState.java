package model.bestelling.states;

import model.bestelling.Bestelling;

public abstract class BestellingState {
    protected Bestelling bestelling;

    public abstract void starten();
    public abstract void afronden();
    public abstract void betalen();
    public abstract void verzenden();
    public abstract void bereiden();
    public abstract void afwerken();
    public abstract void annuleren();
    public abstract void wachten();
}
