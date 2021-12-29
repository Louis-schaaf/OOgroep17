package view.admin.panes;


import controller.admin.OverzichtController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.BelegSoort;
import model.Broodje;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Deze klasse geeft een overzicht van de broodjes en belegsoorten in de "Broodjes/beleg"-tab van de Admin-view.
 */
public class SandwichOverviewPane extends GridPane{
	public OverzichtController controller;
	private TableView<Broodje> table;
	List<Broodje> list;
	ObservableList<Broodje> broodjeObservableList;
	private TableView<BelegSoort> belegTableView;
	List<BelegSoort> list2;
	ObservableList<BelegSoort> belegObservableList;

	public SandwichOverviewPane(OverzichtController controller) {
		this.controller = controller;
		controller.setPane(this);
		list = new ArrayList<>(controller.getBroodjes().values());
		broodjeObservableList = FXCollections.observableList(list);
		list2 = new ArrayList<>(controller.getBeleg().values());
		belegObservableList = FXCollections.observableList(list2);

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
		this.add(new Label("Broodjes:"), 0, 0, 1, 1);
		this.table = this.setUpTable("Broodjes");
		this.add(table, 0, 1);
		this.add(new Label("Beleg:"), 1, 0, 1, 1);
		this.belegTableView = this.setUpTable("Beleg");
		this.add(belegTableView, 1, 1);
	}

	public TableView setUpTable(String type) {
		TableView result = new TableView();
		if (type == "Broodjes") {
			result.setItems(broodjeObservableList); //Als inlezen werkt, moeten we observers vervangen door broodjeObservableList
			TableColumn<Broodje, String> firstNameColumn = new TableColumn<Broodje, String>("Name");
			TableColumn<Broodje, Double> secondNameColumn = new TableColumn<Broodje, Double>("Sale price");
			TableColumn<Broodje, Integer> thirdNameColumn = new TableColumn<Broodje, Integer>("Available stock");
			TableColumn<Broodje, Integer> fourthNameColumn = new TableColumn<Broodje, Integer>("Sold amount");
			firstNameColumn.setCellValueFactory(new PropertyValueFactory<Broodje,String>("name"));
			secondNameColumn.setCellValueFactory(new PropertyValueFactory<Broodje, Double>("salePrice"));
			thirdNameColumn.setCellValueFactory(new PropertyValueFactory<Broodje, Integer>("actualStock"));
			fourthNameColumn.setCellValueFactory(new PropertyValueFactory<Broodje, Integer>("soldAmount"));
			result.getColumns().addAll(firstNameColumn,secondNameColumn,thirdNameColumn,fourthNameColumn);
		} else if (type == "Beleg") {
			result.setItems(belegObservableList); //Als inlezen werkt, moeten we observers1 vervangen door belegObservableList
			TableColumn<BelegSoort, String> firstNameColumn1 = new TableColumn<BelegSoort, String>("Name");
			TableColumn<BelegSoort, Double> secondNameColumn1 = new TableColumn<BelegSoort, Double>("Sale price");
			TableColumn<BelegSoort, Integer> thirdNameColumn1 = new TableColumn<BelegSoort, Integer>("Available stock");
			TableColumn<BelegSoort, Integer> fourthNameColumn1 = new TableColumn<BelegSoort, Integer>("Sold amount");
			firstNameColumn1.setCellValueFactory(new PropertyValueFactory<BelegSoort,String>("name"));
			secondNameColumn1.setCellValueFactory(new PropertyValueFactory<BelegSoort, Double>("salePrice"));
			thirdNameColumn1.setCellValueFactory(new PropertyValueFactory<BelegSoort, Integer>("actualStock"));
			fourthNameColumn1.setCellValueFactory(new PropertyValueFactory<BelegSoort, Integer>("soldAmount"));
			result.getColumns().addAll(firstNameColumn1,secondNameColumn1,thirdNameColumn1,fourthNameColumn1);
		}
		return result;
	}

	public void update(Map<String, Broodje> broodjes,Map<String, BelegSoort> beleg) {
		this.getChildren().remove(table);
		this.getChildren().remove(belegTableView);
		list = new ArrayList<>(broodjes.values());
		broodjeObservableList = FXCollections.observableList(list);
		list2 = new ArrayList<>(beleg.values());
		belegObservableList = FXCollections.observableList(list2);
		this.table = this.setUpTable("Broodjes");
		this.add(table, 0, 1);
		this.belegTableView = this.setUpTable("Beleg");
		this.add(belegTableView, 1, 1);
		this.table.setVisible(false);
		this.table.setVisible(true);
		this.belegTableView.setVisible(false);
		this.belegTableView.setVisible(true);
	}
}
