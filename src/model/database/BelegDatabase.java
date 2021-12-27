package model.database;

import model.BelegSoort;
import model.Broodje;
import model.database.loadSaveStrategies.BelegTekstLoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;

public class BelegDatabase {
    private Map<String, BelegSoort> belegDB;
    private LoadSaveStrategy loadSaveStrategy;

    public BelegDatabase() {
        belegDB = new TreeMap<>();
        setStrategy(LoadSaveStrategyFactory.createLoadSaveStrategy("EXCELBELEG"));
        this.load();
    }

    public void addBeleg(BelegSoort beleg){
        if (this.exists(beleg)) throw new IllegalStateException("Je kan geen beleg twee keer toevoegen.");
        belegDB.put(beleg.getName(),beleg);
    }

    public void setStrategy(LoadSaveStrategy strategy){
        this.loadSaveStrategy = strategy;
    }

    public boolean exists(BelegSoort beleg){
        return belegDB.containsKey(beleg.getName());
    }


    public List<BelegSoort> getAll(){
        Collection<BelegSoort> values = belegDB.values();
        return new ArrayList<>(values);
    }

    public void load() {
        belegDB.putAll(loadSaveStrategy.load());
    }

    public void save() {
        loadSaveStrategy.save(this.getAll());
    }

    public Map<String, BelegSoort> getBelegDB() {
        return belegDB;
    }

    public Map<String, Integer> getVoorraadlijstBeleg() {
        Map result = new TreeMap<String, Integer>();
        List<BelegSoort> beleg = this.getAll();
        for (BelegSoort b : beleg) {
            result.put(b.getName(), b.getActualStock());
            //TODO Werkt nog niet. Geen idee hoe dit komt.
        }
        return result;
    }


    /*public void readFile() {
        File file = new File("src/bestanden/beleg.txt");
        try {
            belegDB = new BelegTekstLoadSaveStrategy().read(file);
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
    }*/
}
