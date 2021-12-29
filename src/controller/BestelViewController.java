package controller;

import jxl.read.biff.BiffException;
import model.*;
import model.bestelStates.BestellingState;
import view.admin.AdminView;
import view.order.OrderView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BestelViewController implements Observer {
    public BestelFacade bestelFacade;
    public OrderView orderView;

    public BestelViewController() {
        setBestelFacade(new BestelFacade());
        //TODO: (LOUIS) waarom luistert die naar alles? Omdat die bij elk van deze events moet updaten.
        this.bestelFacade.addObserver(this, "TOEVOEGEN_BROODJE");
        this.bestelFacade.addObserver(this, "NIEUWE_BESTELLING");
        this.bestelFacade.addObserver(this, "TOEVOEGEN_BELEG");
        this.bestelFacade.addObserver(this, "AFSLUITEN_BESTELLING");
        this.bestelFacade.addObserver(this, "ANNULEER_BESTELLING");
        this.bestelFacade.addObserver(this, "IDENTIEKE_BESTELLIJN");
        this.bestelFacade.addObserver(this, "VERWIJDER_BROODJE");
        this.bestelFacade.addObserver(this, "BETAAL_BESTELLING");
        this.bestelFacade.addObserver(this, "ZEND_NAAR_KEUKEN");
    }

    public void addObserver(Observer observer, String event) {this.bestelFacade.addObserver(observer, event);}

    public void setBestelFacade(BestelFacade bestelFacade) {
        this.bestelFacade = bestelFacade;
    }

    public void setOrderView(OrderView orderView) {
        this.orderView = orderView;
    }

    @Override
    public void update() {
        orderView.update(this.getBestelling());
    }

    public Map<String, Broodje> getBroodjesDB() {
        return this.bestelFacade.getBroodjes();
    }

    public Map<String, BelegSoort> getBelegDB() {
        return this.bestelFacade.getBeleg();
    }

    public Map<String, Integer> getVoorraadBroodjes() {
        return this.bestelFacade.getVoorraadBroodjes();
    }

    public Map<String, Integer> getVoorraadBeleg() {
        return this.bestelFacade.getVoorraadBeleg();
    }

    public BestellingState getState() {
        return this.bestelFacade.getBestellingState();
    }

    public Bestelling getBestelling() {
        return this.bestelFacade.getBestelling();
    }

    public Bestellijn getBestellijn() {
        return this.orderView.getSelected();
    }

    public void startNieuweBestelling() throws BiffException, IOException {
        this.bestelFacade.startNieuweBestelling();
    }
    public void afsluitenBestelling() throws BiffException, IOException {
        String korting = orderView.getKorting();
        this.bestelFacade.afsluitenBestelling();
        this.orderView.updateBedrag(this.bestelFacade.updateBedrag(korting));
    }

    public List<Bestellijn> getLijstBestellijnen() {
        return this.bestelFacade.getLijstBestellijnen();
    }

    public void voegBestellijnToe (String naamBroodje) throws BiffException, IOException {
        this.bestelFacade.voegBestellijnToe(naamBroodje);
        this.orderView.updateBestellijnen();
        this.orderView.updateStatusBroodjesKnoppen(this.getVoorraadBroodjes());
    }

    public void voegBelegToe(String naamBeleg, int bestelLijn) throws BiffException, IOException {
        if (bestelLijn > -1) {
            this.bestelFacade.voegBelegToe(naamBeleg, bestelLijn);
            this.orderView.updateBestellijnen();
            this.orderView.updateStatusBelegKnoppen(this.getVoorraadBeleg());
            this.orderView.updateStatusBroodjesKnoppen(this.getVoorraadBroodjes());
        } else {
            this.orderView.toonError();
        }
    }

    public void annuleerBestelling() throws BiffException, IOException {
        this.bestelFacade.annuleerBestelling();
        this.orderView.updateBestellijnen();
        this.orderView.updateStatusBroodjesKnoppen(this.getVoorraadBroodjes());
        this.orderView.updateStatusBelegKnoppen(this.getVoorraadBeleg());
    }

    public void verwijderBestellijn(Bestellijn selectedBestellijn) throws BiffException, IOException {
        if (selectedBestellijn == null) {
            this.orderView.toonError();
        } else {
            this.bestelFacade.verwijderBestellijn(selectedBestellijn);
            this.orderView.updateBestellijnen();
            this.orderView.updateStatusBroodjesKnoppen(this.getVoorraadBroodjes());
            this.orderView.updateStatusBelegKnoppen(this.getVoorraadBeleg());
        }
    }

    public void voegIdentiekeBestellijnToe(Bestellijn selectedBestellijn) throws BiffException, IOException {
        if (selectedBestellijn == null) {
            this.orderView.toonError();
        } else {
            if (this.bestelFacade.voegIdentiekBestellijnToe(selectedBestellijn)){
                Bestellijn nieuw = selectedBestellijn;
                voegBestellijnToe(nieuw.getNaamBroodje());
                for (String beleg : selectedBestellijn.getNamenBelegLijst()){
                    voegBelegToe(beleg, getLijstBestellijnen().size()-1);
                }
            } else {
                this.orderView.toonError();
            }
        }
    }

    public void betaalBestelling() throws BiffException, IOException{
        this.bestelFacade.betaalBestelling();
    }

    public void stuurBestellingNaarKeuken() throws BiffException, IOException {
        this.bestelFacade.zendBestellingNaarKeuken();
    }
}
