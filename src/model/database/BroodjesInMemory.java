package model.database;

import model.Broodje;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BroodjesInMemory {
    private Map<String, Broodje> broodjesDB = new HashMap<>();

    public void readFile() {
        File file = new File("src/bestanden/broodjes.txt");
        try {
            broodjesDB = new BroodjeTekstReader().load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Broodje> getBroodjesDB() {
        readFile();
        return this.broodjesDB;
    }
}
