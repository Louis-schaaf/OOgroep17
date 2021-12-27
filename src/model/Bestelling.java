package model;

import java.util.ArrayList;
import java.util.List;

public class Bestelling {
    public List<Bestellijn> bestellijnen;

    public Bestelling() {
        this.bestellijnen = new ArrayList<>();
    }

    public List<Bestellijn> getBestellijnen() {
        return bestellijnen;
    }

    public void voegBestellijnToe (Broodje broodje) {
        this.bestellijnen.add(new Bestellijn(broodje));
    }
}
