package model.bestelling.states;

import model.bestelling.Bestelling;

public class Geannuleerd extends BestellingState{
    private Bestelling bestelling;

    public Geannuleerd(Bestelling bestelling) {
        this.bestelling = bestelling;
    }
}
