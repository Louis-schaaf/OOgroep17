package model;

import java.util.ArrayList;
import java.util.List;

public class Bestellijn {
    public String naamBroodje;
    public List<String> namenBeleg;

    public Bestellijn(String naamBroodje) {
        this.naamBroodje = naamBroodje;
        this.namenBeleg = new ArrayList<>();
    }

    public Bestellijn(Broodje broodje) {
        this.naamBroodje = broodje.getName();
        broodje.aanpassenVoorraad(-1);
        this.namenBeleg = new ArrayList<>();
    }

    public String getNaamBroodje() {
        return this.naamBroodje;
    }

    public List<String> getNamenBeleg() {
        return this.namenBeleg;
    }

    public void addNaamBeleg (String naamBeleg) {
        this.namenBeleg.add(naamBeleg);
    }
}

