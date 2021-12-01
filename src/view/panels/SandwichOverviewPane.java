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
import model.Beleg;
import model.Broodje;
import model.database.BelegInMemory;
import model.database.BroodjesInMemory;

import java.util.ArrayList;
import java.util.List;


public class SandwichOverviewPane extends GridPane{
	//private TableView<Speler> table;
	private TableView<Broodje> table = new TableView<>();
	BroodjesInMemory broodjes = new BroodjesInMemory();
	List<Broodje> list = new ArrayList<>(broodjes.getBroodjesDB().values());
	ObservableList<Broodje> broodjeObservableList =  FXCollections.observableList(list);

	private TableView<Beleg> belegTableView = new TableView<>();
	BelegInMemory beleg = new BelegInMemory();
	List<Beleg> list2 = new ArrayList<>(beleg.getBelegDB().values());
	ObservableList<Beleg> belegObservableList =  FXCollections.observableList(list2);
	
	
	public SandwichOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);        
		this.add(new Label("Broodjes:"), 0, 0, 1, 1);
		table.setItems(broodjeObservableList); //Als inlezen werkt, moeten we observers vervangen door broodjeObservableList
		TableColumn<Broodje, String> firstNameColumn = new TableColumn<Broodje, String>("Name");
		TableColumn<Broodje, Double> secondNameColumn = new TableColumn<Broodje, Double>("Sale price");
		TableColumn<Broodje, Integer> thirdNameColumn = new TableColumn<Broodje, Integer>("Available stock");
		TableColumn<Broodje, Integer> fourthNameColumn = new TableColumn<Broodje, Integer>("Sold amount");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Broodje,String>("name"));
		secondNameColumn.setCellValueFactory(new PropertyValueFactory<Broodje, Double>("salePrice"));
		thirdNameColumn.setCellValueFactory(new PropertyValueFactory<Broodje, Integer>("actualStock"));
		fourthNameColumn.setCellValueFactory(new PropertyValueFactory<Broodje, Integer>("soldAmount"));
		table.getColumns().addAll(firstNameColumn,secondNameColumn,thirdNameColumn,fourthNameColumn);
		this.add(table, 0, 1);

		this.add(new Label("Beleg:"), 1, 0, 1, 1);
		belegTableView.setItems(belegObservableList); //Als inlezen werkt, moeten we observers1 vervangen door belegObservableList
		TableColumn<Beleg, String> firstNameColumn1 = new TableColumn<Beleg, String>("Name");
		TableColumn<Beleg, Double> secondNameColumn1 = new TableColumn<Beleg, Double>("Sale price");
		TableColumn<Beleg, Integer> thirdNameColumn1 = new TableColumn<Beleg, Integer>("Available stock");
		TableColumn<Beleg, Integer> fourthNameColumn1 = new TableColumn<Beleg, Integer>("Sold amount");
		firstNameColumn1.setCellValueFactory(new PropertyValueFactory<Beleg,String>("name"));
		secondNameColumn1.setCellValueFactory(new PropertyValueFactory<Beleg, Double>("salePrice"));
		thirdNameColumn1.setCellValueFactory(new PropertyValueFactory<Beleg, Integer>("actualStock"));
		fourthNameColumn1.setCellValueFactory(new PropertyValueFactory<Beleg, Integer>("soldAmount"));
		belegTableView.getColumns().addAll(firstNameColumn1,secondNameColumn1,thirdNameColumn1,fourthNameColumn1);
		this.add(belegTableView, 1, 1);
	}
}
