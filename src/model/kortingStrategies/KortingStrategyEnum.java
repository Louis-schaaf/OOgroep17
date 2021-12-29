package model.kortingStrategies;

/**
 * Deze Enum bevat alle mogelijke strategieën die we kunnen gebruiken om korting te verrekenen op een bestelling.
 */
public enum KortingStrategyEnum {
    GEENKORTING("Geen Korting", "model.kortingStrategies.GeenKortingStrategy"),
    GOEDKOOPSTEGRATIS("Goedkoopste Gratis", "model.kortingStrategies.GoedkoopsteGratisStrategy"),
    TIENPROCENT("Tien Procent", "model.kortingStrategies.TienProcentKortingStrategy");

    private final String omschrijving;
    private final String klasseNaam;

    KortingStrategyEnum(String omschrijving, String klasseNaam){
        this.omschrijving = omschrijving;
        this.klasseNaam = klasseNaam;
    }

    public String getKlasseNaam() {
        return klasseNaam;
    }
}
