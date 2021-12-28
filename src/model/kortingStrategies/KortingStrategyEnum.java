package model.kortingStrategies;

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

    public String getOmschrijving() {
        return omschrijving;
    }
}
