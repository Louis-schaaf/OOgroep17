package controller;

import jxl.read.biff.BiffException;
import model.bestelling.BestelFacade;
import model.bestelling.Bestelling;
import model.Observer;
import model.bestelling.states.BestellingState;
import view.order.OrderView;

import java.io.IOException;
import java.util.Map;

public class BestelViewController implements Observer {
    public BestelFacade bestelFacade;
    public OrderView orderView;

    public BestelViewController() {
        setBestelFacade(new BestelFacade());
        this.bestelFacade.addObserver(this);
        setOrderView(new OrderView(this));
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

    public void voegBestellijnToe (String naamBroodje) throws BiffException, IOException {
        this.bestelFacade.voegBestellijnToe(naamBroodje);
        this.orderView.updateBestellijnen(this.bestelFacade.getLijstBestellijnen());
        this.orderView.updateStatusBroodjesKnoppen(this.getVoorraadBroodjes());
    }

    public void voegBelegToe(String naamBeleg, int bestelLijn) throws BiffException, IOException {
        this.bestelFacade.voegBelegToe(naamBeleg, bestelLijn);
        this.orderView.updateBestellijnen(this.bestelFacade.getLijstBestellijnen());
        this.orderView.updateStatusBelegKnoppen(this.getVoorraadBeleg());
    }

    @Override
    public void update() {
        orderView.update(this.getBestelling());
    }
}
