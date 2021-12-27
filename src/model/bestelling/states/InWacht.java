package model.bestelling.states;

import model.bestelling.Bestelling;

public class InWacht extends BestellingState{
    private Bestelling bestelling;

    public InWacht(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public void starten() {
        bestelling.changeState(new InBestelling(bestelling));
        System.out.println("starten...");
    }

    @Override
    public void afronden() {

    }

    @Override
    public void betalen() {

    }

    @Override
    public void verzenden() {

    }

    @Override
    public void bereiden() {

    }

    @Override
    public void afwerken() {

    }

    @Override
    public void annuleren() {

    }

    @Override
    public void wachten() {

    }
}
