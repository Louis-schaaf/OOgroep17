package view.kitchen;

import controller.KeukenViewController;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Bestelling;
import view.kitchen.panes.KitchenKnoppen;

import java.util.ArrayList;
import java.util.List;

/**
 * Deze klasse maakt de Kitchen-view aan.
 */
public class KitchenView {
	public KeukenViewController controller;
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

	public List<Bestelling> getWachtrij() {
		return wachtrij;
	}

    public void update() {
		this.getWachtrij().addAll(this.controller.getBetaaldeBestellingen());
		this.kitchenKnoppen.update();
		kitchenKnoppen.updateCounter();
		kitchenKnoppen.updateBestellingsInfo();
    }
}
