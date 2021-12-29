package controller.admin;

import model.BestelFacade;
import model.Observer;
import view.admin.panes.SettingsPane;

/**
 * Deze klasse maakt een nieuwe controller aan die de Instellingen-tab van de Admin-view bediend.
 */
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
