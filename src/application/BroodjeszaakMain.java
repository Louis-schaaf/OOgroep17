package application;

import controller.BestelViewController;
import controller.KeukenViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import jxl.read.biff.BiffException;
import model.BestelFacade;
import view.admin.AdminView;
import view.kitchen.KitchenView;
import view.order.OrderView;

import java.io.IOException;

/**
 * Deze klasse start onze applicatie op.
 */
public class BroodjeszaakMain extends Application {
	@Override
	public void start(Stage primaryStage) throws BiffException, IOException {
		BestelFacade bestelFacade = new BestelFacade();
		AdminView adminView = new AdminView(bestelFacade);
		BestelViewController controller = new BestelViewController(bestelFacade);
		OrderView orderView = new OrderView(controller);
		KeukenViewController keukenViewController = new KeukenViewController(bestelFacade);
		KitchenView kitchenView = new KitchenView(keukenViewController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
