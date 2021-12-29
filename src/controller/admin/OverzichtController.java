package controller.admin;

import model.BelegSoort;
import model.BestelFacade;
import model.Broodje;
import model.Observer;
import view.admin.panes.SandwichOverviewPane;
import java.util.Map;

/**
 * Deze klasse maakt een nieuwe controller aan die de Broodjes/beleg-tab van de Admin-view bediend.
 */
public class OverzichtController implements Observer {
    public BestelFacade bestelFacade;
    public SandwichOverviewPane pane;

    public OverzichtController(BestelFacade bestelFacade) {
        setBestelFacade(bestelFacade);
        this.bestelFacade.addObserver(this, "ZEND_NAAR_KEUKEN");
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
