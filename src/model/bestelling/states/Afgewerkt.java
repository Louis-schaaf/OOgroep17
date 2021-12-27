package model.bestelling.states;

import model.bestelling.Bestelling;

public class Afgewerkt extends BestellingState{
    private Bestelling bestelling;

    public Afgewerkt(Bestelling bestelling) {
        this.bestelling = bestelling;
    }
}
