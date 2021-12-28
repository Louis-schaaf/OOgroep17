package controller;

import jxl.read.biff.BiffException;
import model.BestelFacade;
import model.Bestellijn;
import model.Bestelling;
import model.Observer;
import model.bestelStates.BestellingState;
import view.order.OrderView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BestelViewController implements Observer {
    public BestelFacade bestelFacade;
    public OrderView orderView;

    public BestelViewController() {
        setBestelFacade(new BestelFacade());
        this.bestelFacade.addObserver(this, "TOEVOEGEN_BROODJE");
        this.bestelFacade.addObserver(this, "NIEUWE_BESTELLING");
        this.bestelFacade.addObserver(this, "TOEVOEGEN_BELEG");
    }

    public void setBestelFacade(BestelFacade bestelFacade) {
        this.bestelFacade = bestelFacade;
    }

    public void setOrderView(OrderView orderView) {
        this.orderView = orderView;
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

    public void startNieuweBestelling() throws BiffException, IOException {
        this.bestelFacade.startNieuweBestelling();
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
        this.bestelFacade.voegBelegToe(naamBeleg, bestelLijn);
        this.orderView.updateBestellijnen();
        this.orderView.updateStatusBelegKnoppen(this.getVoorraadBeleg());
    }

    public void annuleerBestelling() throws BiffException, IOException {
    }

    @Override
    public void update() {
        orderView.update(this.getBestelling());
    }
}
