package model.bestelling.states;

import model.bestelling.Bestelling;

public class Afgesloten extends BestellingState{
    private Bestelling bestelling;

    public Afgesloten(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public void betalen () {
        bestelling.setState(bestelling.getBetaald());
    }

    public void annuleren() {
        bestelling.setState(bestelling.getInWacht());
    }
}
