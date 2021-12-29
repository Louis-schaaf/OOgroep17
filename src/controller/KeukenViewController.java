package controller;

import jxl.read.biff.BiffException;
import model.BestelFacade;
import model.Bestelling;
import model.Observer;
import view.kitchen.KitchenView;

import java.io.IOException;
import java.util.List;

public class KeukenViewController implements Observer {
    public BestelFacade bestelFacade;
    public KitchenView kitchenView;

    public KeukenViewController(BestelFacade bestelFacade) {
        setBestelFacade(bestelFacade);
        this.bestelFacade.addObserver(this, "ZEND_NAAR_KEUKEN");
    }

    public void setBestelFacade(BestelFacade bestelFacade) {
        this.bestelFacade = bestelFacade;
    }

    public void setKitchenView(KitchenView kitchenView) {
        this.kitchenView = kitchenView;
    }

    public List<Bestelling> getBetaaldeBestellingen () {
        return this.bestelFacade.getBetaaldeBestellingen();
    }

    public Bestelling getBestelling() {
        return this.bestelFacade.getBestelling();
    }

    public void bestellingAfgewerkt() {
        this.bestelFacade.bestellingAfgewerkt();
    }

    @Override
    public void update() throws IOException, BiffException {
        this.kitchenView.update();
    }


}
