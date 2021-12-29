package model.kortingStrategies;

import java.util.ArrayList;

/**
 * Deze interface geeft alle mogelijke acties die de verschillende KortingStrategies kunnen uitvoeren.
 */
public interface KortingStrategy {
    double berekenPrijs(ArrayList<Double> bedragPerBroodje);
}
