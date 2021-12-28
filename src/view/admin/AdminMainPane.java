package view.admin;


import controller.admin.OverzichtController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.admin.panes.SandwichOverviewPane;
import view.admin.panes.SettingsPane;
import view.admin.panes.StatisticsPane;

public class AdminMainPane extends BorderPane {
	public AdminMainPane(){		
	    TabPane tabPane = new TabPane(); 	    
        //Tab spelVerloopTab = new Tab("Spelverloop");
        SandwichOverviewPane sandwichOverviewPane = new SandwichOverviewPane(new OverzichtController());
        SettingsPane settingsPane = new SettingsPane();
        StatisticsPane statisticsPane = new StatisticsPane();
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
