package controller.admin;

import jxl.read.biff.BiffException;
import model.BelegSoort;
import model.BestelFacade;
import model.Broodje;
import model.Observer;
import view.admin.panes.StatisticsPane;

import java.io.IOException;
import java.util.Map;

public class StatistiekenController implements Observer {
    public BestelFacade bestelFacade;
    public StatisticsPane statisticsPane;

    public StatistiekenController() {
        setBestelFacade(new BestelFacade());
        this.bestelFacade.addObserver(this, "BETAAL_BESTELLING");
    }

    private void setBestelFacade(BestelFacade bestelFacade) {
        this.bestelFacade = bestelFacade;
    }

    public void setStatisticsPane(StatisticsPane statisticsPane) {
        this.statisticsPane = statisticsPane;
    }

    public Map<String, Integer> getVerkochteBroodjes() {
        return this.bestelFacade.getVerkochteBroodjes();
    }

    public Map<String, Integer> getVerkochtBeleg() {
        return this.bestelFacade.getVerkochtBeleg();
    }

    public Map<String, Broodje> getBroodjes() {
        return this.bestelFacade.getBroodjes();
    }

    public Map<String, BelegSoort> getBeleg() {
        return this.bestelFacade.getBeleg();
    }

    @Override
    public void update() throws IOException, BiffException {
        statisticsPane.update(this.getVerkochteBroodjes(), this.getVerkochtBeleg());
    }
}
