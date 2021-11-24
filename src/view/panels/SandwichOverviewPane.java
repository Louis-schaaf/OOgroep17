package view.panels;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
	}

	public void refresh(){
		broodjeObservableList = FXCollections.observableArrayList(list);
		table.setItems(broodjeObservableList);
		table.refresh();
	}
}
