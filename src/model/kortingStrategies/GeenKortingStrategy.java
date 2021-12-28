package model.kortingStrategies;

import model.Bestellijn;
import model.Bestelling;

public class GeenKortingStrategy implements KortingStrategy{

    @Override
    public double berekenPrijs(Bestelling bestelling) {
        return 0; //TODO
    }
}
