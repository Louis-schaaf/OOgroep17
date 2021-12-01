package model.database;

import model.Broodje;

public class BroodjeTekstReader extends TekstLoadTemplate{
    @Override
    Object maakObject(String[] tokens) {
        Broodje broodje = new Broodje(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        return broodje;
    }

    @Override
    Object getKey(String[] tokens) {
        return tokens[0];
    }
}
