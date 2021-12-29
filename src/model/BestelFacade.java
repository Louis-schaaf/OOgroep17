package model;

import jxl.read.biff.BiffException;
import model.bestelStates.BestellingState;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import model.kortingStrategies.KortingStrategy;
import model.kortingStrategies.KortingStrategyFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BestelFacade implements Subject {
    public BroodjesDatabase broodjesDatabase;
    public BelegDatabase belegDatabase;
    public Bestelling bestelling;
    public List<Bestelling> betaaldeBestellingen;

    public BestelFacade() {
        this.broodjesDatabase = new BroodjesDatabase();
        this.belegDatabase = new BelegDatabase();
        this.bestelling = new Bestelling();
        this.betaaldeBestellingen = new ArrayList<>();
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

    public Map<String, Integer> getVerkochteBroodjes() {
        return this.broodjesDatabase.getVerkochteBroodjes();
    }

    public Map<String, Integer> getVerkochtBeleg() {
        return this.belegDatabase.getVerkochtBeleg();
    }

    public Map<String, Broodje> getBroodjes() {
        return this.broodjesDatabase.getBroodjes();
    }

    public Map<String, BelegSoort> getBeleg() {
        return this.belegDatabase.getBelegDB();
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

    public void verwijderBestellijn(Bestellijn selectedBestellijn) {
        Bestellijn b = this.bestelling.verwijderBestellijn(selectedBestellijn);
        Broodje broodje = this.broodjesDatabase.getBroodje(b.getNaamBroodje());
        broodje.aanpassenVoorraad(1);
        for (String s : b.getNamenBelegLijst()) {
            BelegSoort beleg = this.belegDatabase.getBeleg(s);
            beleg.aanpassenVoorraad(1);
        }
    }

    public void betaalBestelling() throws BiffException, IOException{
        for (Bestellijn b : this.getLijstBestellijnen()) {
            Broodje broodje = this.broodjesDatabase.getBroodje(b.getNaamBroodje());
            broodje.aanpassenVerkochtAantal(1);
            for (String s : b.getNamenBelegLijst()) {
                BelegSoort beleg = this.belegDatabase.getBeleg(s);
                beleg.aanpassenVerkochtAantal(1);
            }
        }
        this.bestelling.betalen();
        Bestelling bestelling = new Bestelling();
        bestelling.setVolgnummer(this.bestelling.getVolgnummer());
        List<Bestellijn> bestellijns = this.bestelling.getBestellijnen();
        bestelling.setBestellijnen(bestelling, bestellijns);
        this.betaaldeBestellingen.add(bestelling);
        notifyObservers("BETAAL_BESTELLING");
    }

    public void zendBestellingNaarKeuken() throws BiffException, IOException {
        this.bestelling.verzenden();
        notifyObservers("ZEND_NAAR_KEUKEN");
    }

    public List<Bestelling> getBetaaldeBestellingen() {
        return this.betaaldeBestellingen;
    }

    public double updateBedrag(String korting) {
        ArrayList<Double> bedragPerBroodje = new ArrayList<>();
        for (Bestellijn b : this.getLijstBestellijnen()) {
            double bedrag = 0;
            Broodje broodje = this.broodjesDatabase.getBroodje(b.getNaamBroodje());
            bedrag += broodje.getSalePrice();
            for (String s : b.getNamenBelegLijst()) {
                BelegSoort beleg = this.belegDatabase.getBeleg(s);
                bedrag += beleg.getSalePrice();
            }
            bedragPerBroodje.add(bedrag);
        }
        KortingStrategy kortingStrategy = KortingStrategyFactory.createKortingStrategy(korting);
        return kortingStrategy.berekenPrijs(bedragPerBroodje);
    }

    public boolean voegIdentiekBestellijnToe(Bestellijn selectedBestellijn) {
        boolean mogelijk = true;
        Broodje broodje = this.broodjesDatabase.getBroodje(selectedBestellijn.getNaamBroodje());
        if (broodje.getActualStock() < 1){
            mogelijk = false;
        }
        for (String belegSoortString: selectedBestellijn.getNamenBelegLijst()){
            BelegSoort belegSoort = this.belegDatabase.getBeleg(belegSoortString);
            if (belegSoort.getActualStock() < 1){
                mogelijk = false;
            }
        }
        return mogelijk;
    }

    public void setProperties() {
        Instellingen.write();
    }

    public void bestellingAfgewerkt() {
        this.betaaldeBestellingen.remove(0);
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
}
