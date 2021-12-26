package model.database;

import model.Broodje;
import model.database.loadSaveStrategies.BroodjesExcelLoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.util.*;

public class BroodjesDatabase {
    private Map<String,Broodje> broodjes;
    private LoadSaveStrategy loadSaveStrategy;

    public BroodjesDatabase() {
        broodjes = new TreeMap<>();
        setStrategy(LoadSaveStrategyFactory.createLoadSaveStrategy("EXCELBROODJES"));
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
        //TODO
        //broodje.setActualStock(broodje.getActualStock()-1);

    }
}
