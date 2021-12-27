package model.bestelling.states;

import model.bestelling.Bestelling;

public class InBereiding implements BestellingState {
    private Bestelling bestelling;

    public InBereiding(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public void afwerken() {
        bestelling.setState(bestelling.getAfgewerkt());
    }
}