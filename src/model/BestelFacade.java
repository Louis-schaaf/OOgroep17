package model;

import model.database.BelegDatabase;
import model.database.BroodjesDatabase;

import java.util.List;
import java.util.Map;

public class BestelFacade {
    public BroodjesDatabase broodjesDatabase;
    public BelegDatabase belegDatabase;
    public Bestelling bestelling;

    public BestelFacade() {
        this.broodjesDatabase = new BroodjesDatabase();
        this.belegDatabase = new BelegDatabase();
        this.bestelling = new Bestelling();
    }

    public void voegBestellijnToe(String naamBroodje) {
        Broodje broodje = this.broodjesDatabase.getBroodje(naamBroodje);
        this.bestelling.voegBestellijnToe(broodje);

    }

    public List<Bestellijn> getLijstBestellijnen() {
        return this.bestelling.getBestellijnen();
    }

    public Map<String, Integer> getVoorraadBroodjes() {
        return this.broodjesDatabase.getVoorraadlijstBroodjes();
    }

    public Map<String, Integer> getVoorraadBeleg() {
        return this.belegDatabase.getVoorraadlijstBeleg();
    }

    public Bestelling getBestelling() {
        return this.bestelling;
    }
}
