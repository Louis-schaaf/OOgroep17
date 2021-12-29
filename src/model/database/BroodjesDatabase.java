package model.database;

import model.Broodje;
import model.Instellingen;
import model.database.loadSaveStrategies.BroodjesExcelLoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.util.*;

public class BroodjesDatabase {
    private Map<String,Broodje> broodjes;
    private LoadSaveStrategy loadSaveStrategy;

    public BroodjesDatabase() {
        broodjes = new TreeMap<>();
        setStrategy(LoadSaveStrategyFactory.createLoadSaveStrategy(Instellingen.getLoad() + "BROODJES"));
        this.load();
    }

    public void voegItemToe(Broodje broodje){
        if (this.exists(broodje)) throw new IllegalStateException("Je kan geen broodje twee keer toevoegen.");
        broodjes.put(broodje.getName(),broodje);
    }

    public void setStrategy(LoadSaveStrategy strategy){
        this.loadSaveStrategy = strategy;
    }

    public boolean exists(Broodje broodje){
        return broodjes.containsKey(broodje.getName());
    }


    public List<Broodje> getAll(){
        Collection<Broodje> values = broodjes.values();
        return new ArrayList<>(values);
    }

    public void load() {
        broodjes.putAll(loadSaveStrategy.load());
    }

    public void save() {
        loadSaveStrategy.save(this.getAll());
    }

    public Map<String, Broodje> getBroodjes() {
        return broodjes;
    }

    public void sellBroodjes(Broodje broodje) {
        broodje.setActualStock(broodje.getActualStock()-1);
    }

    public Broodje getBroodje(String naamBroodje) {
        return this.broodjes.get(naamBroodje);
    }

    public Map<String, Integer> getVoorraadlijstBroodjes() {
        Map result = new TreeMap<String, Integer>();
        List<Broodje> broodjes = this.getAll();
        for (Broodje b : broodjes) {
            result.put(b.getName(), b.getActualStock());
        }
        return result;
    }

    public Map<String, Integer> getVerkochteBroodjes() {
        Map result = new TreeMap<String, Integer>();
        List<Broodje> broodjes = this.getAll();
        for (Broodje b : broodjes) {
            result.put(b.getName(), b.getSoldAmount());
        }
        return result;
    }
}
