package view.panels;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Broodje;
import model.database.BroodjesInMemory;

import java.util.ArrayList;
import java.util.List;


public class SandwichOverviewPane extends GridPane{
	//private TableView<Speler> table;
	private TableView<Broodje> table = new TableView<>();
	BroodjesInMemory broodjes = new BroodjesInMemory();
	List<Broodje> list = new ArrayList<Broodje>(broodjes.getBroodjesDB().values());
	ObservableList<Broodje> broodjeObservableList;
	
	
	public SandwichOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);        
		this.add(new Label("Broodjes:"), 0, 0, 1, 1);
		ListView<String> listView = new ListView<>();
		listView.getItems().addAll("Action 1", "Action 2", "Action 3", "Action 4");
		listView.setPrefWidth(260);
		listView.setMaxWidth(260);
		listView.setPrefHeight(150);
		this.setConstraints(listView, 0, 2, 1, 1);
		this.getChildren().add(listView);
	}
}
