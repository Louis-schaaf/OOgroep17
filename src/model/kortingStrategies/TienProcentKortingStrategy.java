package model.kortingStrategies;

import java.util.ArrayList;

/**
 * Deze klasse stelt de strategie voor die gebruikt wordt indien er eeb korting wordt verrekend op de bestelling waarbij
 * de klant 10 procent korting krijgt op de gehele bestelling.
 */
public class TienProcentKortingStrategy implements KortingStrategy{

    @Override
    public double berekenPrijs(ArrayList<Double> bedragPerBroodje) {
        double bedrag = 0;
        for (Double bedragBroodje : bedragPerBroodje){
            bedrag += bedragBroodje * 0.9;
        }
        bedrag = bedrag * 100;
        bedrag = Math.round(bedrag);
        bedrag = bedrag / 100;
        return bedrag;
    }
}
