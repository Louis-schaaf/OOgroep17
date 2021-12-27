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

    public void startNieuweBestelling() throws BiffException, IOException {
        this.bestelling.starten();
        notifyObservers("NIEUWE_BESTELLING");
    }

    public void voegBestellijnToe(String naamBroodje) throws BiffException, IOException {
        Broodje broodje = this.broodjesDatabase.getBroodje(naamBroodje);
        this.bestelling.voegBestellijnToe(broodje);
    }

    public void voegBelegToe(String naamBeleg, int bestelLijn) throws BiffException, IOException {
        BelegSoort beleg = this.belegDatabase.getBeleg(naamBeleg);
        this.bestelling.voegBelegToe(beleg, bestelLijn);
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
        Bestellijn bestellijn1 = this.bestelling.verwijderBestellijn(bestellijn);
        Broodje broodje = this.broodjesDatabase.getBroodje(bestellijn1.getNaamBroodje());
        broodje.aanpassenVoorraad(1);
        for (String s : bestellijn1.getNamenBeleg()) {
            BelegSoort beleg = this.belegDatabase.getBeleg(s);
            beleg.aanpassenVoorraad(1);
        }
        notifyObservers();
    }

    public void annuleerBestelling() throws BiffException, IOException {
        for (Bestellijn l : this.getLijstBestellijnen()) {
            Broodje broodje = this.broodjesDatabase.getBroodje(l.getNaamBroodje());
            broodje.aanpassenVoorraad(1);
            for (String s : l.getNamenBeleg()) {
                BelegSoort beleg = this.belegDatabase.getBeleg(s);
                beleg.aanpassenVoorraad(1);
            }
        }
        this.bestelling.resetBestelling();
        notifyObservers();
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
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) throws IOException, BiffException {
        for (Observer observer : observers.get(event)) {
                observer.update();
            }
        }
    }
