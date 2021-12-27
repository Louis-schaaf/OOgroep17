package controller;

import model.bestelling.BestelFacade;
import model.bestelling.Bestelling;
import model.Observer;
import view.order.OrderView;

import java.util.Map;

public class BestelViewController implements Observer {
    public BestelFacade bestelFacade;
    public OrderView orderView;

    public BestelViewController() {
        this.bestelFacade = new BestelFacade();
        this.orderView = new OrderView(this);
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

    public void voegBestellijnToe (String naamBroodje) {
        this.bestelFacade.voegBestellijnToe(naamBroodje);
        this.orderView.updateBestellijnen(this.bestelFacade.getLijstBestellijnen());
        this.orderView.updateStatusBroodjesKnoppen(this.bestelFacade.getVoorraadBroodjes());
    }

    @Override
    public void update() {
        orderView.refresh();
    }

    public Bestelling getBestelling() {
        return this.bestelFacade.getBestelling();
    }
}
