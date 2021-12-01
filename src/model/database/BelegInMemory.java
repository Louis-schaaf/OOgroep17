package model.database;

import model.Beleg;
import model.Broodje;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BelegInMemory {
    private Map<String, Beleg> belegDB = new HashMap<>();

    public void readFile() {
        File file = new File("src/bestanden/beleg.txt");
        try {
            belegDB = new BelegTekstReader().load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Beleg> getBelegDB() {
        readFile();
        return this.belegDB;
    }
}
