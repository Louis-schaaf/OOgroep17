package model.database;

import model.Beleg;
import model.Broodje;

public class BelegTekstReader extends TekstLoadTemplate{
    @Override
    Object maakObject(String[] tokens) {
        Beleg beleg = new Beleg(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        return beleg;
    }

    @Override
    Object getKey(String[] tokens) {
        return tokens[0];
    }
}
