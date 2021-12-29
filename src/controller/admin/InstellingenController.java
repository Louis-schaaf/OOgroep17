package controller.admin;

import model.BestelFacade;
import model.Observer;
import view.admin.panes.SettingsPane;
import view.admin.panes.StatisticsPane;

public class InstellingenController implements Observer {
    public BestelFacade bestelFacade;
    public SettingsPane pane;

    public InstellingenController() {
        setBestelFacade(new BestelFacade());
        this.bestelFacade.addObserver(this, "BETAAL_BESTELLING");
    }

    public void setBestelFacade(BestelFacade bestelFacade) {
        this.bestelFacade = bestelFacade;
    }

    @Override
    public void update() {
        pane.update();
    }
}
