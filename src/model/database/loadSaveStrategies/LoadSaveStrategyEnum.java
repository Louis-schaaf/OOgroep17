package model.database.loadSaveStrategies;

public enum LoadSaveStrategyEnum {
    EXCELBROODJES("ExcelBroodjes", "model.database.loadSaveStrategies.BroodjesExcelLoadSaveStrategy"),
    TEKSTBROODJES("TekstBroodjes", "model.database.loadSaveStrategies.BroodjesTekstLoadSaveStrategy"),
    EXCELBELEG("ExcelBeleg", "model.database.loadSaveStrategies.BelegExcelLoadSaveStrategy"),
    TEKSTBELEG("TekstBeleg", "model.database.loadSaveStrategies.BelegTekstLoadSaveStrategy");

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
