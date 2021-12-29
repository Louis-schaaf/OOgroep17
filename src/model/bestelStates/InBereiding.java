package model.bestelStates;

import model.Bestelling;

public class InBereiding implements BestellingState {
    Bestelling bestelling;

    public InBereiding(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public void starten() {
        bestelling.changeState(new InBestelling(bestelling));
        System.out.println("starten...");
    }

    @Override
    public void afronden() {
        bestelling.changeState(new Afgesloten(bestelling));
        System.out.println("afsluiten...");
    }

    @Override
    public void betalen() {
        bestelling.changeState(new Betaald(bestelling));
        System.out.println("betalen...");
    }

    @Override
    public void verzenden() {
        bestelling.changeState(new InKeuken(bestelling));
        System.out.println("verzenden...");
    }

    @Override
    public void bereiden() {
        bestelling.changeState(new InBereiding(bestelling));
        System.out.println("bereiden...");
    }

    @Override
    public void afwerken() {
        bestelling.changeState(new InWacht(bestelling));
        System.out.println("afwerken...");
    }

    @Override
    public void annuleren() {
        bestelling.changeState(new InWacht(bestelling));
        System.out.println("annuleren...");
    }
}
