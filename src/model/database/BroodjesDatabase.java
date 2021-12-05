package model.database;

import model.Broodje;
import model.database.loadSaveStrategies.BroodjesTekstLoadSaveStrategy;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BroodjesDatabase {
    private Map<String, Broodje> broodjesDB = new HashMap<>();

    public void readFile() {
        File file = new File("src/bestanden/broodjes.txt");
        try {
            broodjesDB = new BroodjesTekstLoadSaveStrategy().load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Broodje> getBroodjesDB() {
        readFile();
        return this.broodjesDB;
    }
}
