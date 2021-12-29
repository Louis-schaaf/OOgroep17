package model.bestelStates;

import model.Bestelling;

/**
 * Deze klasse stelt de staat voor waarin de bestelling zich bevindt als de bestelling nog niet is gestart.
 * Deze staat wordt bereikt door op de "Annuleer"-knop of de "Naar Keuken"-knop in de Order-view te drukken.
 */
public class InWacht implements BestellingState{
    Bestelling bestelling;
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
