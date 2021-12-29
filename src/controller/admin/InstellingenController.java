package controller.admin;

import jxl.read.biff.BiffException;
import model.BelegSoort;
import model.BestelFacade;
import model.Broodje;
import model.Observer;
import view.admin.panes.SettingsPane;
import view.admin.panes.StatisticsPane;

import java.io.IOException;
import java.util.Map;

public class InstellingenController implements Observer {
    public BestelFacade bestelFacade;
    public SettingsPane settingsPane;

    public InstellingenController(BestelFacade bestelFacade) {
        setBestelFacade(bestelFacade);
        this.bestelFacade.addObserver(this, "BETAAL_BESTELLING");
    }

    public void setBestelFacade(BestelFacade bestelFacade) {
        this.bestelFacade = bestelFacade;
    }

    public void setSettingsPane(SettingsPane settingsPane) {
        this.settingsPane = settingsPane;
    }

    public void setProperties() {
        this.bestelFacade.setProperties();
    }

    @Override
    public void update() {
        settingsPane.update();
    }
}
