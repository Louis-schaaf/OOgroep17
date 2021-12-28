package model.kortingStrategies;

import model.Bestelling;

import java.util.ArrayList;

public class TienProcentKortingStrategy implements KortingStrategy{

    @Override
    public double berekenPrijs(ArrayList<Double> bedragPerBroodje) {
        double bedrag = 0;
        for (Double bedragBroodje : bedragPerBroodje){
            bedrag += bedragBroodje * 0.9;
        }
        return bedrag;
    }
}
