package model.database.loadSaveStrategies;

/**
 * Deze Enum bevat alle mogelijke laadstrategieën die we kunnen gebruiken om broodjes en beleg in te laden en opslaan.
 */
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
}
