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
        this.afgesloten = new Afgesloten(this);
        this.betaald = new Betaald(this);
        this.inBereiding = new InBereiding(this);
        this.inBestelling = new InBestelling(this);
        this.inKeuken = new InKeuken(this);
        this.inWacht = new InWacht(this);
        this.bestellijnen = new ArrayList<>();
        this.state = this.inWacht;
    }

    public List<Bestellijn> getBestellijnen() {
        return bestellijnen;
    }

    public void voegBestellijnToe (Broodje broodje) {
        this.bestellijnen.add(new Bestellijn(broodje));
    }

    public void voegBelegToe(BelegSoort beleg, int bestelLijn) {
        this.bestellijnen.get(bestelLijn).addNaamBeleg(beleg);
    }

    public void setState(BestellingState state) {
        this.state = state;
    }

    public BestellingState getInWacht() {
        return this.inWacht;
    }

    public BestellingState getAfgesloten() {
        return this.afgesloten;
    }

    public BestellingState getBetaald() {
        return this.betaald;
    }

    public BestellingState getInBereiding() {
        return this.inBereiding;
    }

    public BestellingState getInBestelling() {
        return this.inBestelling;
    }

    public BestellingState getInKeuken() {
        return this.inKeuken;
    }

    public BestellingState getState() {
        return this.state;
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
