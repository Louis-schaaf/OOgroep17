package model.bestelling.states;

import model.bestelling.Bestelling;

public class InWacht extends BestellingState{
    private Bestelling bestelling;

    public InWacht(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public void starten() {
        bestelling.setState(bestelling.getInBestelling());
    }
}
