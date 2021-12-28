package model.kortingStrategies;

import model.Bestelling;

public class GoedkoopsteGratisStrategy implements KortingStrategy{

    @Override
    public double berekenPrijs(Bestelling bestelling) {
        return 0; //TODO
    }
}
