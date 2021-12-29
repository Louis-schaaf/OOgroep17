package model.bestelStates;

/**
 * Deze interface geeft alle mogelijke acties die de verschillende States kunnen uitvoeren.
 */
public interface BestellingState {
    void starten();
    void afronden();
    void betalen();
    void verzenden();
    void bereiden();
    void afwerken();
    void annuleren();
}
