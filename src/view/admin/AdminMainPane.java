package view.admin;


import controller.admin.InstellingenController;
import controller.admin.OverzichtController;
import controller.admin.StatistiekenController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import model.BestelFacade;
import view.admin.panes.SandwichOverviewPane;
import view.admin.panes.SettingsPane;
import view.admin.panes.StatisticsPane;

public class AdminMainPane extends BorderPane {
	public AdminMainPane(BestelFacade bestelFacade){
	    TabPane tabPane = new TabPane(); 	    
        //Tab spelVerloopTab = new Tab("Spelverloop");
        SandwichOverviewPane sandwichOverviewPane = new SandwichOverviewPane(new OverzichtController(bestelFacade));
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        sandwichOverviewPane.getColumnConstraints().addAll(col1, col2);
        SettingsPane settingsPane = new SettingsPane(new InstellingenController(bestelFacade));
        StatisticsPane statisticsPane = new StatisticsPane(new StatistiekenController(bestelFacade));
        Tab broodjesTab = new Tab("Broodjes/Beleg",sandwichOverviewPane);
        Tab instellingTab = new Tab("Instellingen",settingsPane);
        Tab statistiekTab = new Tab("Statistieken", statisticsPane);
        //tabPane.getTabs().add(spelVerloopTab);
        tabPane.getTabs().add(broodjesTab);
        tabPane.getTabs().add(statistiekTab);
        tabPane.getTabs().add(instellingTab);
        this.setCenter(tabPane);
	}
}
