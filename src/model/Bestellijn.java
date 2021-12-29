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
        String result = "";
        while(containsduplicates() != null) {
            String duplicate = containsduplicates();
            ArrayList<String> arrayList = getDuplicates(namen, duplicate);
            result+= arrayList.size()+"x "+duplicate+" ";
            namen.removeAll(arrayList);
        }
        while(namen.size() != 0) {
            result+= namen.get(0)+" ";
            namen.remove(0);
        }
        return result;
    }

    private ArrayList<String> getDuplicates(List<String> namenBeleg, String duplicate) {
        ArrayList<String> arrayList = new ArrayList<>();
        for(String beleg : namenBeleg) {
            if (beleg.equals(duplicate)) {
                arrayList.add(beleg);
            }
        }
        return arrayList;
    }

    private String containsduplicates() {
        for (int i  = 0; i != this.getNamenBelegLijst().size(); i++) {
            for (int j = 0; j != this.getNamenBelegLijst().size();j++) {
                if (i!=j && getNamenBelegLijst().get(i).equals(getNamenBelegLijst().get(j))) {
                    return this.getNamenBelegLijst().get(i);
                }
            }
        }
        return null;
    }

    public List<String> getNamenBelegLijst() {
        return this.namenBeleg;
    }

    public void addNaamBeleg (BelegSoort naamBeleg) {
        this.namenBeleg.add(naamBeleg.getName());
        naamBeleg.aanpassenVoorraad(-1);
    }
}

