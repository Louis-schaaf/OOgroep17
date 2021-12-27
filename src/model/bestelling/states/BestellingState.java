package model.bestelling.states;

public interface BestellingState {
    default void starten(){
        throw new IllegalArgumentException("Kan de bestelling niet beginnen.");
    }
    default void afronden(){
        throw new IllegalArgumentException("Kan de bestelling niet afronden.");
    }
    default void betalen(){
        throw new IllegalArgumentException("Kan de bestelling niet betalen.");
    }
    default void verzenden(){
        throw new IllegalArgumentException("Kan de bestelling niet naar de keuken verzenden.");
    }
    default void bereiden(){
        throw new IllegalArgumentException("Kan de bestelling niet bereiden.");
    }
    default void afwerken(){
        throw new IllegalArgumentException("Kan de bestelling niet afleveren.");
    }
    default void annuleren(){
        throw new IllegalArgumentException("Kan de bestelling niet annuleren.");
    }
    default void wachten(){
        throw new IllegalArgumentException("Kan niet wachten op een nieuwe bestelling.");
    }
}
