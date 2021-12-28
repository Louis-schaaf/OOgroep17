package model.bestelStates;

import model.Bestelling;

public class InBestelling extends BestellingState{

    public InBestelling(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void starten() {

    }

    @Override
    public void afronden() {
        bestelling.changeState(new Afgesloten(bestelling));
        System.out.println("afsluiten...");
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
