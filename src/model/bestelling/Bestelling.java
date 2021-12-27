package model.bestelling;

import jxl.read.biff.BiffException;
import model.BelegSoort;
import model.Broodje;
import model.Observer;
import model.Subject;
import model.bestelling.states.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bestelling {

    public List<Bestellijn> bestellijnen;
    public BestellingState state;

    public Bestelling() {
        bestellijnen = new ArrayList<>();
        this.state = new InWacht(this);
    }

    public List<Bestellijn> getBestellijnen() {
        return bestellijnen;
    }

    public BestellingState getState() {
        return state;
    }



    public void voegBestellijnToe (Broodje broodje) {
        this.bestellijnen.add(new Bestellijn(broodje));
    }

    public void voegBelegToe(BelegSoort beleg, int bestelLijn) {
        this.bestellijnen.get(bestelLijn).addNaamBeleg(beleg);
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

    public void wachten () {
        this.state.wachten();
    }

    public void resetBestelling() {
        changeState(new InWacht(this));
        this.bestellijnen.clear();
    }
}
