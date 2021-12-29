package view.admin.panes;

import controller.admin.StatistiekenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.BelegSoort;
import model.Broodje;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticsPane extends GridPane {
    public StatistiekenController controller;
    private BarChart broodjesChart;
    List<Broodje> list;
    ObservableList<Broodje> broodjeObservableList;
    private BarChart belegChart;
    List<BelegSoort> list2;
    ObservableList<BelegSoort> belegObservableList;

    public StatisticsPane(StatistiekenController controller) {
        this.controller = controller;
        controller.setStatisticsPane(this);
        list = new ArrayList<>(controller.getBroodjes().values());
        broodjeObservableList = FXCollections.observableList(list);
        list2 = new ArrayList<>(controller.getBeleg().values());
        belegObservableList = FXCollections.observableList(list2);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(new Label("Bekijk hieronder de meest verkochte soorten broodjes en beleg."), 0, 0);
        this.setUpBarChartBroodjes();
        this.setUpBarChartBeleg();
    }

    private void setUpBarChartBroodjes() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Broodjes");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Verkocht aantal");
        this.broodjesChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataSeries1 = new XYChart.Series();
        for (Broodje b : this.broodjeObservableList) {
            dataSeries1.getData().add(new XYChart.Data(b.getName(), b.getSoldAmount()));
        }
        this.broodjesChart.getData().add(dataSeries1);
        this.broodjesChart.setLegendVisible(false);
        this.add(this.broodjesChart, 0, 1);
    }

    private void setUpBarChartBeleg() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Beleg");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Verkocht aantal");
        this.belegChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataSeries1 = new XYChart.Series();
        for (BelegSoort b : this.belegObservableList) {
            dataSeries1.getData().add(new XYChart.Data(b.getName(), b.getSoldAmount()));
        }
        this.belegChart.getData().add(dataSeries1);
        this.belegChart.setLegendVisible(false);
        this.add(this.belegChart, 1, 1);
    }

    public void update(Map<String, Broodje> broodjes, Map<String, BelegSoort> beleg) {
        XYChart.Series dataBroodjes = new XYChart.Series();
        XYChart.Series dataBeleg = new XYChart.Series();
        for (Broodje b : broodjes.values()) {
            dataBroodjes.getData().add(new XYChart.Data(b.getName(), b.getSoldAmount()));
        }
        for (BelegSoort b : beleg.values()) {
            dataBeleg.getData().add(new XYChart.Data(b.getName(), b.getSoldAmount()));
        }
        this.broodjesChart.getData().set(0, dataBroodjes);
        this.belegChart.getData().set(0, dataBeleg);
        this.broodjesChart.setVisible(false);
        this.broodjesChart.setVisible(true);
        this.belegChart.setVisible(false);
        this.belegChart.setVisible(true);
        //TODO update past niks aan
    }
}
