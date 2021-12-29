package model;

import javafx.beans.value.ObservableValue;
import model.BelegSoort;
import model.Broodje;

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

    public String getNamenBeleg() {
        String result = "";
        for (String beleg : this.namenBeleg) {
            if (this.namenBeleg.get(this.namenBeleg.size()-1).equals(beleg)) {
                result+=beleg;
            } else {
                result+=beleg+", ";
            }
        }
        return result;
    }

    public String getNamenBelegWithCount() {
        List<String> namen = this.getNamenBelegLijst();
        return "";
    }

    public List<String> getNamenBelegLijst() {
        return this.namenBeleg;
    }

    public void addNaamBeleg (BelegSoort naamBeleg) {
        this.namenBeleg.add(naamBeleg.getName());
        naamBeleg.aanpassenVoorraad(-1);
    }
}

