package model.bestelling.states;

import model.bestelling.Bestelling;

public class Afgesloten extends BestellingState{

    public Afgesloten(Bestelling bestelling) {
        super(bestelling);
    }


    @Override
    public void starten() {

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
        bestelling.changeState(new Afgesloten(bestelling));
        System.out.println("annuleren...");
    }

    @Override
    public void wachten() {

    }
}
