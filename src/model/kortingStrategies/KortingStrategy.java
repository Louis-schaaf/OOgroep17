package model.kortingStrategies;

import model.Bestelling;

import java.util.ArrayList;

public interface KortingStrategy {
    double berekenPrijs(ArrayList<Double> bedragPerBroodje);
}
