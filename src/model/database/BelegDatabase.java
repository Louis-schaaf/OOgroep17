package model.database;

import model.BelegSoort;
import model.database.loadSaveStrategies.BelegTekstLoadSaveStrategy;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BelegDatabase {
    private Map<String, BelegSoort> belegDB = new HashMap<>();

    public void readFile() {
        File file = new File("src/bestanden/beleg.txt");
        try {
            belegDB = new BelegTekstLoadSaveStrategy().load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, BelegSoort> getBelegDB() {
        readFile();
        return this.belegDB;
    }
}
