package model.kortingStrategies;

import java.util.ArrayList;

/**
 * Deze klasse stelt de strategie voor die gebruikt wordt indien er eeb korting wordt verrekend op de bestelling waarbij
 * de klant het goedkoopste broodje gratis krijgt.
 */
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
        bedrag = bedrag - goedkoopste;
        bedrag = bedrag * 100;
        bedrag = Math.round(bedrag);
        bedrag = bedrag / 100;
        return bedrag;
    }
}
