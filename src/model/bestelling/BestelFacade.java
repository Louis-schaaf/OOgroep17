package model.bestelling;

import jxl.read.biff.BiffException;
import model.Broodje;
import model.Observer;
import model.Subject;
import model.bestelling.Bestellijn;
import model.bestelling.Bestelling;
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

    public void voegBestellijnToe(String naamBroodje) {
        Broodje broodje = this.broodjesDatabase.getBroodje(naamBroodje);
        this.bestelling.voegBestellijnToe(broodje);
        //TODO Observers toevoegen.

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

    public void resetBestelling() {
        this.bestelling.resetBestelling();
    }

    public Bestelling startNieuweBestelling() {
        this.resetBestelling();
        //TODO state op inBestelling zetten
        return this.bestelling;
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
