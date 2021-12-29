package application;
	
import com.sun.scenario.Settings;
import controller.BestelViewController;
import controller.KeukenViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import jxl.read.biff.BiffException;
import model.Instellingen;
import model.database.BroodjesDatabase;
import model.database.loadSaveStrategies.BroodjesExcelLoadSaveStrategy;
import view.admin.AdminView;
import view.kitchen.KitchenView;
import view.order.OrderView;

import java.io.IOException;


public class BroodjeszaakMain extends Application {
	@Override
	public void start(Stage primaryStage) throws BiffException, IOException {
		AdminView adminView = new AdminView();
		BestelViewController controller = new BestelViewController();
		OrderView orderView = new OrderView(controller);
		KeukenViewController keukenViewController = new KeukenViewController();
		controller.addObserver(keukenViewController, "ZendNaarKeuken");

		KitchenView kitchenView = new KitchenView(keukenViewController);
		kitchenView.setBestelViewController(controller);
		String fileUseType = "Excel";
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
