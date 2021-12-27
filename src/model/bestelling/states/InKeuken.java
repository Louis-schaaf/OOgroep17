package model.bestelling.states;

import model.bestelling.Bestelling;

public class InKeuken implements BestellingState {
    private Bestelling bestelling;

    public InKeuken(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public void bereiden() {
        bestelling.setState(bestelling.getInBereiding());
    }
}
