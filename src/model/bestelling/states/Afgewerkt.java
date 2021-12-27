package model.bestelling.states;

import model.bestelling.Bestelling;

public class Afgewerkt implements BestellingState{
    private Bestelling bestelling;

    public Afgewerkt(Bestelling bestelling) {
        this.bestelling = bestelling;
    }
}
