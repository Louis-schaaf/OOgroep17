package model.database;

import model.BelegSoort;
import model.Broodje;
import model.database.loadSaveStrategies.BelegTekstLoadSaveStrategy;
import model.database.loadSaveStrategies.BroodjesTekstLoadSaveStrategy;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BroodjesDatabase {
    private Map<String, Broodje> broodjesDB = new TreeMap<>();

    public void readFile() {
        File file = new File("src/bestanden/broodjes.txt");
        try {
            broodjesDB = new BroodjesTekstLoadSaveStrategy().load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile() {
        File file = new File ("src/bestanden/broodjes.txt");
        ArrayList<Broodje> arrayList = (ArrayList<Broodje>) this.broodjesDB.values();
        ArrayList<ArrayList<String>> args = new ArrayList<>();
        for (Broodje b : arrayList) {
            args.add(b.getArrayList());
        }
        new BroodjesTekstLoadSaveStrategy().write(file,args);
    }

    public Map<String, Broodje> getBroodjesDB() {
        readFile();
        return this.broodjesDB;
    }
}
