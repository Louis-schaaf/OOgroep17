package model.database;

import model.BelegSoort;
import model.Instellingen;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.util.*;

/**
 * Deze klasse stelt de database met beleg-items voor die we in onze broodjeszaak gebruiken.
 */
public class BelegDatabase {
    private Map<String, BelegSoort> belegDB;
    private LoadSaveStrategy loadSaveStrategy;

    public BelegDatabase() {
        belegDB = new TreeMap<>();
        setStrategy(LoadSaveStrategyFactory.createLoadSaveStrategy(Instellingen.getLoad().toUpperCase() + "BELEG"));
        this.load();
    }

    public void setStrategy(LoadSaveStrategy strategy){
        this.loadSaveStrategy = strategy;
    }

    public List<BelegSoort> getAll(){
        Collection<BelegSoort> values = belegDB.values();
        return new ArrayList<>(values);
    }

    public Map<String, BelegSoort> getBelegDB() {
        return belegDB;
    }

    public Map<String, Integer> getVoorraadlijstBeleg() {
        Map result = new TreeMap<String, Integer>();
        List<BelegSoort> beleg = this.getAll();
        for (BelegSoort b : beleg) {
            result.put(b.getName(), b.getActualStock());
        }
        return result;
    }

    public Map<String, Integer> getVerkochtBeleg() {
        Map result = new TreeMap<String, Integer>();
        List<BelegSoort> beleg = this.getAll();
        for (BelegSoort b : beleg) {
            result.put(b.getName(), b.getSoldAmount());
        }
        return result;
    }

    public BelegSoort getBeleg(String naamBeleg) {
        return this.belegDB.get(naamBeleg);
    }

    public void load() {
        belegDB.putAll(loadSaveStrategy.load());
    }

    public void save() {
        loadSaveStrategy.save(this.getAll());
    }
}
