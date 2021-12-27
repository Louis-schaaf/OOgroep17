package model.bestelling.states;

import model.bestelling.Bestelling;

public class InBestelling implements BestellingState{
    private Bestelling bestelling;

    public InBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public void afronden() {
        bestelling.setState(bestelling.getAfgesloten());
    }

    @Override
    public void annuleren() {
        bestelling.setState(bestelling.getGeannuleerd());
    }
}
