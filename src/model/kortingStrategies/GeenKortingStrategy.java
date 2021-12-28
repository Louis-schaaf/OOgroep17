package model.kortingStrategies;

import model.Bestellijn;
import model.Bestelling;

import java.util.ArrayList;

public class GeenKortingStrategy implements KortingStrategy{

    @Override
    public double berekenPrijs(ArrayList<Double> bedragPerBroodje) {
        double bedrag = 0;
        for (Double bedragBroodje : bedragPerBroodje){
            bedrag += bedragBroodje;
        }
        return bedrag;
    }
}
