package model.bestelling;

import jxl.read.biff.BiffException;
import model.BelegSoort;
import model.Broodje;
import model.Observer;
import model.Subject;
import model.bestelling.states.BestellingState;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;

import java.io.IOException;
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

    public void annuleerBestelling() {
        this.bestelling.resetBestelling();
    }

    public void startNieuweBestelling() throws BiffException, IOException {
        this.bestelling.starten();
        notifyObservers();
    }

    public void voegBestellijnToe(String naamBroodje) throws BiffException, IOException {
        Broodje broodje = this.broodjesDatabase.getBroodje(naamBroodje);
        this.bestelling.voegBestellijnToe(broodje);
        notifyObservers();
    }

    public void voegBelegToe(String naamBeleg, int bestelLijn) throws BiffException, IOException {
        BelegSoort beleg = this.belegDatabase.getBeleg(naamBeleg);
        this.bestelling.voegBelegToe(beleg, bestelLijn);
        notifyObservers();
    }

    public void voegIdentiekeBestellijnToe(int bestelLijn) throws BiffException, IOException {
        Bestellijn bestellijn = this.bestelling.getBestellijn(bestelLijn);
        Broodje broodje = this.broodjesDatabase.getBroodje(bestellijn.getNaamBroodje());
        int index = this.bestelling.voegBestellijnToe(broodje);
        for (String s : bestellijn.getNamenBeleg()) {
            BelegSoort beleg = this.belegDatabase.getBeleg(s);
            this.bestelling.voegBelegToe(beleg, index);
        }
        notifyObservers();
    }

    public void verwijderBestellijn(int bestellijn) throws BiffException, IOException {
        Bestellijn bestellijnTeVerwijderen = this.bestelling.getBestellijn(bestellijn);
        Broodje broodje = this.broodjesDatabase.getBroodje(bestellijnTeVerwijderen.getNaamBroodje());
        int index = this.bestelling.voegBestellijnToe(broodje);

        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() throws IOException, BiffException {
        for (Observer observer: observers) {
            observer.update();
        }
    }
}
