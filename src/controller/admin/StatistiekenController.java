package controller.admin;

import jxl.read.biff.BiffException;
import model.BelegSoort;
import model.BestelFacade;
import model.Broodje;
import model.Observer;
import view.admin.panes.StatisticsPane;

import java.io.IOException;
import java.util.Map;

/**
 * Deze klasse maakt een nieuwe controller aan die de Statistieken-tab van de Admin-view bediend.
 */
public class StatistiekenController implements Observer {
    public BestelFacade bestelFacade;
    public StatisticsPane statisticsPane;

    public StatistiekenController(BestelFacade bestelFacade) {
        setBestelFacade(bestelFacade);
        this.bestelFacade.addObserver(this, "ZEND_NAAR_KEUKEN");
    }

    private void setBestelFacade(BestelFacade bestelFacade) {
        this.bestelFacade = bestelFacade;
    }

    public void setStatisticsPane(StatisticsPane statisticsPane) {
        this.statisticsPane = statisticsPane;
    }

    public Map<String, Broodje> getBroodjes() {
        return this.bestelFacade.getBroodjes();
    }

    public Map<String, BelegSoort> getBeleg() {
        return this.bestelFacade.getBeleg();
    }

    @Override
    public void update() throws IOException, BiffException {
        statisticsPane.update(this.getBroodjes(), this.getBeleg());
    }
}
