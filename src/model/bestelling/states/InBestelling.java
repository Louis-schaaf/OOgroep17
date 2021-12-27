package model.bestelling.states;

import model.bestelling.Bestelling;

public class InBestelling extends BestellingState{
    private Bestelling bestelling;

    public InBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public void afronden() {
        bestelling.setState(bestelling.getAfgesloten());
    }

    public void annuleren() {
        bestelling.setState(bestelling.getGeannuleerd());
    }
}
