package model.database.loadSaveStrategies;

import model.Broodje;
import utilities.TekstLoadSaveTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BroodjesTekstLoadSaveStrategy extends TekstLoadSaveTemplate implements LoadSaveStrategy{
    File file = new File("src/bestanden/broodjes.txt");
    @Override
    public Object maakObject(String[] tokens) {
        Broodje broodje = new Broodje(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        return broodje;
    }

    @Override
    public Object getKey(String[] tokens) {
        return tokens[0];
    }

    @Override
    public Map load() {
        try {
            return read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(List list) {
    }
}
