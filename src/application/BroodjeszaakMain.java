package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.database.BroodjesDatabase;
import view.AdminView;
import view.KitchenView;
import view.OrderView;


public class BroodjeszaakMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		AdminView adminView = new AdminView();
		OrderView orderView = new OrderView();
		KitchenView kitchenView = new KitchenView();
		String fileUseType = "Excel";
		BroodjesDatabase broodjesInMemory = new BroodjesDatabase();
		broodjesInMemory.readFile();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
