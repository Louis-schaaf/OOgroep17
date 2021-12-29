package controller.admin;

import model.BelegSoort;
import model.BestelFacade;
import model.Broodje;
import model.Observer;
import view.admin.AdminView;
import view.admin.panes.SandwichOverviewPane;
import view.order.OrderView;

import java.util.Map;

public class OverzichtController implements Observer {
    public BestelFacade bestelFacade;
    public SandwichOverviewPane pane;

    public OverzichtController() {
        setBestelFacade(new BestelFacade());
        this.bestelFacade.addObserver(this, "ZendNaarKeuken");
    }

    public void setBestelFacade(BestelFacade bestelFacade) {
        this.bestelFacade = bestelFacade;
    }

    public void setPane(SandwichOverviewPane pane) {
        this.pane = pane;
    }

    public Map<String, Broodje> getBroodjes() {
        return this.bestelFacade.getBroodjes();
    }

    public Map<String, BelegSoort> getBeleg() {
        return this.bestelFacade.getBeleg();
    }

    @Override
    public void update() {
        pane.update(this.getBroodjes(), this.getBeleg());
    }
}
