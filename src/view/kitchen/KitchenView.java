package view.kitchen;

import controller.BestelViewController;
import controller.KeukenViewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Bestelling;
import view.kitchen.panes.KitchenKnoppen;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class KitchenView {
	public KeukenViewController controller;
	public BestelViewController bestelViewController;
	private KitchenKnoppen kitchenKnoppen;
	private Stage stage = new Stage();

	private ArrayList<Bestelling> wachtrij;

	
	public KitchenView(KeukenViewController controller){
		VBox root = new VBox();
		this.controller = controller;
		wachtrij = new ArrayList<>();
		kitchenKnoppen = new KitchenKnoppen(controller);
		stage.setTitle("KITCHEN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(680);
		stage.setY(470);
		Scene scene = new Scene(root, 650, 200);			
		stage.setScene(scene);
		stage.sizeToScene();
		root.getChildren().add(kitchenKnoppen);
		stage.show();
		controller.setKitchenView(this);
	}

	public void setBestelViewController(BestelViewController bestelViewController) {
		this.bestelViewController = bestelViewController;
	}

	public void updateCounter() {
		kitchenKnoppen.updateCounter();
		kitchenKnoppen.updateBestellingsInfo();
	}

	public ArrayList<Bestelling> getWachtrij() {
		return wachtrij;
	}
}
