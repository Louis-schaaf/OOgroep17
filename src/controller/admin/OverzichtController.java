package controller.admin;

import model.BestelFacade;
import model.Observer;
import view.admin.AdminView;
import view.admin.panes.SandwichOverviewPane;
import view.order.OrderView;

public class OverzichtController implements Observer {
    public BestelFacade bestelFacade;
    public SandwichOverviewPane pane;

    public OverzichtController() {
        setBestelFacade(new BestelFacade());
        this.bestelFacade.addObserver(this, "BETAAL_BESTELLING");
    }

    public void setBestelFacade(BestelFacade bestelFacade) {
        this.bestelFacade = bestelFacade;
    }

    public void setPane(SandwichOverviewPane pane) {
        this.pane = pane;
    }

    @Override
    public void update() {
        pane.update();
    }
}
