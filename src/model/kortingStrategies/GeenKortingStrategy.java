package model.kortingStrategies;

import model.Bestellijn;
import model.Bestelling;

import java.util.ArrayList;

import static jdk.nashorn.internal.objects.NativeMath.round;

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
