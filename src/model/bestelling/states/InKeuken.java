package model.bestelling.states;

import model.bestelling.Bestelling;

public class InKeuken extends BestellingState {
    private Bestelling bestelling;

    public InKeuken(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public void bereiden() {
        bestelling.setState(bestelling.getInBereiding());
    }
}
