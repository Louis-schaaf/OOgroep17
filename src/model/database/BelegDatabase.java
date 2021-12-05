package model.database;

import model.BelegSoort;
import model.database.loadSaveStrategies.BelegTekstLoadSaveStrategy;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;

public class BelegDatabase {
    private Map<String, BelegSoort> belegDB = new TreeMap<>();

    public void readFile() {
        File file = new File("src/bestanden/beleg.txt");
        try {
            belegDB = new BelegTekstLoadSaveStrategy().load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile() {
        File file = new File ("src/bestanden/beleg.txt");
        ArrayList<BelegSoort> arrayList = (ArrayList<BelegSoort>) this.belegDB.values();
        ArrayList<ArrayList<String>> args = new ArrayList<>();
        for (BelegSoort b : arrayList) {
            args.add(b.getArrayList());
        }
        new BelegTekstLoadSaveStrategy().write(file,args);
    }

    public Map<String, BelegSoort> getBelegDB() {
        readFile();
        return this.belegDB;
    }
}
