package model.database.loadSaveStrategies;

import model.BelegSoort;
import utilities.TekstLoadSaveTemplate;

import java.util.List;
import java.util.Map;

public class BelegTekstLoadSaveStrategy extends TekstLoadSaveTemplate implements LoadSaveStrategy{
    @Override
    public Object maakObject(String[] tokens) {
        BelegSoort beleg = new BelegSoort(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        return beleg;
    }

    @Override
    public Object getKey(String[] tokens) {
        return tokens[0];
    }

    @Override
    public Map load() {
        return null;
    }

    @Override
    public void save(List list) {

    }
}
