package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.database.BroodjesInMemory;
import view.AdminView;
import view.KitchenView;
import view.OrderView;


public class BroodjeszaakMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		AdminView adminView = new AdminView();
		OrderView orderView = new OrderView();
		KitchenView kitchenView = new KitchenView();
		BroodjesInMemory broodjesInMemory = new BroodjesInMemory();
		broodjesInMemory.readFile();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
