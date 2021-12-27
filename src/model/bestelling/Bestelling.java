package model.bestelling;

import jxl.read.biff.BiffException;
import model.Broodje;
import model.Observer;
import model.Subject;
import model.bestelling.states.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bestelling {
    private BestellingState afgesloten;
    private BestellingState afgewerkt;
    private BestellingState betaald;
    private BestellingState geannuleerd;
    private BestellingState inBereiding;
    private BestellingState inBestelling;
    private BestellingState inKeuken;
    private BestellingState inWacht;
    public List<Bestellijn> bestellijnen;
    public BestellingState state;

    public Bestelling() {
        this.bestellijnen = new ArrayList<>();
        this.state = getInWacht();
    }

    public List<Bestellijn> getBestellijnen() {
        return bestellijnen;
    }

    public void voegBestellijnToe (Broodje broodje) {
        this.bestellijnen.add(new Bestellijn(broodje));
    }

    public void setState(BestellingState state) {
        this.state = state;
    }

    public BestellingState getAfgesloten() {
        return this.afgesloten;
    }

    public BestellingState getAfgewerkt() {
        return this.afgewerkt;
    }

    public BestellingState getBetaald() {
        return this.betaald;
    }

    public BestellingState getGeannuleerd() {
        return this.geannuleerd;
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

    public BestellingState getInWacht() {
        return this.inWacht;
    }

    public BestellingState getState() {
        return this.state;
    }

    public void resetBestelling() {
        this.state = getInWacht();
        this.bestellijnen.clear();
    }
}
