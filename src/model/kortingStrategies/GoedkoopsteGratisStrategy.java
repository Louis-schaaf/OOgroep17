package model.kortingStrategies;

import model.Bestelling;

import java.util.ArrayList;

public class GoedkoopsteGratisStrategy implements KortingStrategy{

    @Override
    public double berekenPrijs(ArrayList<Double> bedragPerBroodje) {
        double bedrag = 0;
        double goedkoopste = 99999;
        for (Double bedragBroodje: bedragPerBroodje){
            bedrag += bedragBroodje;
            if (bedragBroodje < goedkoopste){
                goedkoopste = bedragBroodje;
            }
        }
        return bedrag - goedkoopste;
    }
}
