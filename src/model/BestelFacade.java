package model;

import jxl.read.biff.BiffException;
import model.bestelStates.BestellingState;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BestelFacade implements Subject {
    public BroodjesDatabase broodjesDatabase;
    public BelegDatabase belegDatabase;
    public Bestelling bestelling;

    public BestelFacade() {
        this.broodjesDatabase = new BroodjesDatabase();
        this.belegDatabase = new BelegDatabase();
        this.bestelling = new Bestelling();
    }

    public BestellingState getBestellingState() {
        return this.bestelling.getState();
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

    public void annuleerBestelling() throws BiffException, IOException {
        for (Bestellijn b : this.getLijstBestellijnen()) {
            Broodje broodje = this.broodjesDatabase.getBroodje(b.getNaamBroodje());
            broodje.aanpassenVoorraad(1);
            for (String s : b.getNamenBelegLijst()) {
                BelegSoort beleg = this.belegDatabase.getBeleg(s);
                beleg.aanpassenVoorraad(1);
            }
        }
        this.bestelling.resetBestelling();
        notifyObservers("ANNULEER_BESTELLING");
    }

    public void startNieuweBestelling() throws BiffException, IOException {
        this.bestelling.starten();
        notifyObservers("NIEUWE_BESTELLING");
    }
    public void afsluitenBestelling() throws BiffException, IOException{
        this.bestelling.afronden();
        notifyObservers("AFSLUITEN_BESTELLING");
    }

    public void voegBestellijnToe(String naamBroodje) throws BiffException, IOException {
        Broodje broodje = this.broodjesDatabase.getBroodje(naamBroodje);
        this.bestelling.voegBestellijnToe(broodje);
        notifyObservers("TOEVOEGEN_BROODJE");
    }

    public void voegBelegToe(String naamBeleg, int bestelLijn) throws BiffException, IOException {
        BelegSoort beleg = this.belegDatabase.getBeleg(naamBeleg);
        this.bestelling.voegBelegToe(beleg, bestelLijn);
        notifyObservers("TOEVOEGEN_BELEG");
    }

    @Override
    public void addObserver(Observer observer, String event) {
        if (observers.containsKey(event)){
            observers.get(event).add(observer);
        }else {
            observers.put(event, new ArrayList<Observer>());
            observers.get(event).add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer, String event) {
        observers.remove(observer, event);
    }

    @Override
    public void notifyObservers(String event) throws IOException, BiffException {
        for (Observer observer : observers.get(event)) {
                observer.update();
            }
        }

    public double updateBedrag() {
        double bedrag = 0;
        for (Bestellijn b : this.getLijstBestellijnen()) {
            Broodje broodje = this.broodjesDatabase.getBroodje(b.getNaamBroodje());
            bedrag += broodje.getSalePrice();
            for (String s : b.getNamenBelegLijst()) {
                BelegSoort beleg = this.belegDatabase.getBeleg(s);
                bedrag += beleg.getSalePrice();
            }
        }
        return bedrag;
    }
}
