package application;
	
import controller.BestelViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.database.BroodjesDatabase;
import model.database.loadSaveStrategies.BroodjesExcelLoadSaveStrategy;
import view.admin.AdminView;
import view.kitchen.KitchenView;
import view.order.OrderView;


public class BroodjeszaakMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		AdminView adminView = new AdminView();
		OrderView orderView = new OrderView(new BestelViewController());
		KitchenView kitchenView = new KitchenView();
		String fileUseType = "Excel";

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
