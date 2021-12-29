package model.kortingStrategies;

import java.util.ArrayList;

/**
 * Deze klasse stelt de strategie voor die gebruikt wordt indien er geen korting wordt verrekend op de bestelling.
 */
public class GeenKortingStrategy implements KortingStrategy{

    @Override
    public double berekenPrijs(ArrayList<Double> bedragPerBroodje) {
        double bedrag = 0;
        for (Double bedragBroodje : bedragPerBroodje){
            bedrag += bedragBroodje;
        }
        bedrag = bedrag * 100;
        bedrag = Math.round(bedrag);
        bedrag = bedrag / 100;
        return bedrag;
    }
}
