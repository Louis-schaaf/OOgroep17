package model;

/**
 * Deze Enum bevat alle mogelijke bestellingsevents die onze applicatie gebruikt.
 */
public enum BestellingEvents {
    NIEUWE_BESTELLING("NieuweBestelling", "model.bestelling.bestelFacade.startNieuweBestelling"),
    TOEVOEGEN_BROODJE("BroodjeToevoegen", "model.bestelling.bestelFacade.voegBestellijnToe"),
    TOEVOEGEN_BELEG("BelegToevoegen", "model.bestelling.bestelFacade.voegBelegToe"),
    IDENTIEKE_BESTELLIJN("IdentiekBroodjeToevoegen", "model.bestelling.bestelFacade.voegIdentiekBroodjeToe"),
    VERWIJDER_BROODJE("BroodjeVerwijderen", "model.bestelling.bestelFacade.verwijderBestellijn"),
    AFSLUITEN_BESTELLING("AfsluitenBestelling", "model.bestelling.bestelFacade.afsluitenBestelling"),
    ANNULEER_BESTELLING("AnnuleerBestelling", "model.bestelling.bestelFacade.annuleerBestelling"),
    BETAAL_BESTELLING("BetaalBestelling", "model.bestelling.bestelFacade.betaalBestelling"),
    ZEND_NAAR_KEUKEN("ZendNaarKeuken", "idk");

    private final String omschrijving;
    private final String methodeNaam;

    BestellingEvents(String omschrijving, String klasseNaam){
        this.omschrijving = omschrijving;
        this.methodeNaam = klasseNaam;
    }
}
