package model;

public enum BestellingEvents {
    NIEUWE_BESTELLING("NieuweBestelling", "model.bestelling.bestelFacade.startNieuweBestelling"),
    TOEVOEGEN_BROODJE("BroodjeToevoegen", "model.bestelling.bestelFacade.voegBestellijnToe"),
    TOEVOEGEN_BELEG("BelegToevoegen", "model.bestelling.bestelFacade.voegBelegToe"),
    IDENTIEKE_BESTELLIJN("IdentiekBroodjeToevoegen", "model.bestelling.bestelFacade.voegIdentiekBroodjeToe"),
    VERWIJDER_BROODJE("BroodjeVerwijderen", "model.bestelling.bestelFacade.verwijderBestellijn"),
    AFSLUITEN_BESTELLING("AfsluitenBestelling", "model.bestelling.bestelFacade.afsluitenBestelling"),
    ANNULEER_BESTELLING("AnnuleerBestelling", "model.bestelling.bestelFacade.annuleerBestelling"),
    BETAAL_BESTELLING("BetaalBestelling", "model.bestelling.bestelFacade.betaalBestelling");

    private final String omschrijving;
    private final String methodeNaam;

    BestellingEvents(String omschrijving, String klasseNaam){
        this.omschrijving = omschrijving;
        this.methodeNaam = klasseNaam;
    }

    public String getMethodeNaam() {
        return methodeNaam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }
}
