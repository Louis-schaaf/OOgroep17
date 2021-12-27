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
    private BestellingState afgesloten;
    private BestellingState betaald;
    private BestellingState inBereiding;
    private BestellingState inBestelling;
    private BestellingState inKeuken;
    private BestellingState inWacht;


    public List<Bestellijn> bestellijnen;
    public BestellingState state;

    public Bestelling() {
        this.state = new InWacht(this);
    }

    public List<Bestellijn> getBestellijnen() {
        return bestellijnen;
    }

    public int voegBestellijnToe (Broodje broodje) {
        this.bestellijnen.add(new Bestellijn(broodje));
        return this.bestellijnen.size() - 1;
    }

    public void voegBelegToe(BelegSoort beleg, int bestelLijn) {
        this.bestellijnen.get(bestelLijn).addNaamBeleg(beleg);
    }

    public Bestellijn verwijderBestellijn(int bestellijn) {
        return this.bestellijnen.remove(bestellijn);
    }

    public void changeState(BestellingState state) {
        this.state = state;
    }

    public BestellingState getState() {
        return state;
    }

    public Bestellijn getBestellijn(int i) {
        return this.bestellijnen.get(i);
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
        this.state = inWacht;
        this.bestellijnen.clear();
    }
}
