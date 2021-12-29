package model.bestelStates;

import model.Bestelling;

public interface BestellingState {
    public void starten();
    public void afronden();
    public void betalen();
    public void verzenden();
    public void bereiden();
    public void afwerken();
    public void annuleren();
}
