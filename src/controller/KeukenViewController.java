package controller;

import jxl.read.biff.BiffException;
import model.Observer;
import view.kitchen.KitchenView;

import java.io.IOException;

public class KeukenViewController implements Observer {
    public KitchenView kitchenView;

    public KeukenViewController() {

    }

    public void setKitchenView(KitchenView kitchenView) {
        this.kitchenView = kitchenView;
    }



    @Override
    public void update(String event) throws IOException, BiffException {
        if (event.equals("ZendNaarKeuken")) {
            kitchenView.getWachtrij().add(kitchenView.getBestelViewController().getBestelling());
            kitchenView.updateCounter();
        }


    }
}
