package model.database.loadSaveStrategies;

public enum LoadSaveStrategyEnum {
    EXCELBROODJES("ExcelBroodjes", "domain.loadSaveStrategies.BroodjesExcelLoadSaveStrategy"),
    TEKSTBROODJES("TekstBroodjes", "domain.loadSaveStrategies.BroodjesTekstLoadSaveStrategy"),
    EXCELBELEG("ExcelBroodjes", "domain.loadSaveStrategies.BelegExcelLoadSaveStrategy"),
    TEKSTBELEG("TekstBroodjes", "domain.loadSaveStrategies.BelegTekstLoadSaveStrategy");

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
