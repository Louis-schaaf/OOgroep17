package model.kortingStrategies;

import model.Bestelling;

public interface KortingStrategy {
    double berekenPrijs(Bestelling bestelling);
}
