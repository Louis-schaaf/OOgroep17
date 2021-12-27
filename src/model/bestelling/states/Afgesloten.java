package model.bestelling.states;

import model.bestelling.Bestelling;

public class Afgesloten implements BestellingState{
    private Bestelling bestelling;

    public Afgesloten(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public void betalen () {
        bestelling.setState(bestelling.getBetaald());
    }

    @Override
    public void annuleren() {
        bestelling.setState(bestelling.getGeannuleerd());
    }
}
