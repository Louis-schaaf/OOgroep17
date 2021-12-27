package model.bestelling.states;

import model.bestelling.Bestelling;

public class Betaald extends BestellingState {
    private Bestelling bestelling;

    public Betaald(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public void verzenden() {
        bestelling.setState(bestelling.getInKeuken());
    }
}
