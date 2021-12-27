package model.bestelling.states;

import model.bestelling.Bestelling;

public class InWacht implements BestellingState{
    private Bestelling bestelling;

    public InWacht(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public void starten() {
        bestelling.setState(bestelling.getInBestelling());
    }
}
