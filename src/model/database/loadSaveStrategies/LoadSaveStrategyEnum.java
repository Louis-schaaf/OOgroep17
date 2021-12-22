package model.database.loadSaveStrategies;

public enum LoadSaveStrategyEnum {
    EXCEL("Excel", "domain.loadSaveStrategies.BroodjesExcelLoadSaveStrategy"),
    TEKST("Tekst", "domain.loadSaveStrategies.BroodjesTekstLoadSaveStrategy");

    private final String omschrijving;
    private final String klasseNaam;

    LoadSaveStrategyEnum(String omschrijving, String klasseNaam){
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
