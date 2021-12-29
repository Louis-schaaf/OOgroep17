package model;

import model.bestelStates.BestellingState;
import model.bestelStates.InWacht;

import java.util.ArrayList;
import java.util.List;

public class Bestelling {
    private int volgnummer;
    public List<Bestellijn> bestellijnen;
    public BestellingState state;

    public Bestelling() {
        bestellijnen = new ArrayList<>();
        this.state = new InWacht(this);
    }

    public int getIndex (Bestellijn bestellijn) {
        return this.bestellijnen.indexOf(bestellijn);
    }

    public void setVolgnummer(int volgnummer) {
        this.volgnummer = volgnummer;
    }

    public int getVolgnummer() {
        return volgnummer;
    }

    public List<Bestellijn> getBestellijnen() {
        return bestellijnen;
    }

    public BestellingState getState() {
        return state;
    }

    public Bestellijn containsDuplicates() {
        boolean found = false;
        for (int j = 0; j != bestellijnen.size();j++) {
            for (int i = 0; i != bestellijnen.size(); i++) {
                if (found == false &&bestellijnen.get(j).naamBroodje == bestellijnen.get(i).naamBroodje && bestellijnen.get(j).getNamenBeleg().equals(bestellijnen.get(i).getNamenBeleg()) && i!=j) {
                    found = true;
                    return bestellijnen.get(i);
                }
            }
        }
        return null;
    }

    public void voegBestellijnToe (Broodje broodje) {
        this.bestellijnen.add(new Bestellijn(broodje));
    }

    public void voegBelegToe(BelegSoort beleg, int bestelLijn) {
        this.bestellijnen.get(bestelLijn).addNaamBeleg(beleg);
    }

    public void resetBestelling() {
        this.state.annuleren();
        this.bestellijnen.clear();
    }

    public void changeState(BestellingState state) {
        this.state = state;
    }

    public void starten () {
        this.state.starten();
    }

    public void afronden () {
        this.state.afronden();
    }

    public void betalen () {
        this.state.betalen();
    }

    public void verzenden () {
        this.state.verzenden();
    }

    public void bereiden () {
        this.state.bereiden();
    }

    public void afwerken () {
        this.state.afwerken();
    }

    public void annuleren () {
        this.state.annuleren();
    }

    public Bestellijn verwijderBestellijn(Bestellijn selectedBestellijn) {
        return this.bestellijnen.remove(this.getIndex(selectedBestellijn));
    }

    public void setBestellijnen(Bestelling bestelling, List<Bestellijn> bestellijns) {
        for (Bestellijn bestellijn: bestellijns){
            bestelling.voegBestellijnToe(bestellijn);
        }
    }

    public void voegBestellijnToe (Bestellijn bestellijn) {
        this.bestellijnen.add(bestellijn);
    }
}
