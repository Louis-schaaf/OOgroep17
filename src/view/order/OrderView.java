package view.order;

import controller.BestelViewController;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.bestelling.Bestellijn;
import view.order.panes.OrderAfsluitingBestelling;
import view.order.panes.OrderBroodjes;
import view.order.panes.OrderNieuweBestelling;
import view.order.panes.OrderOptions;

import java.util.List;
import java.util.Map;

public class OrderView {
	private Stage stage = new Stage();
	private OrderAfsluitingBestelling orderAfsluitingBestelling;
	private OrderNieuweBestelling orderNieuweBestelling;
	private OrderOptions orderOptions;
	private OrderBroodjes orderBroodjes;

		
	public OrderView(BestelViewController controller){
		VBox root = new VBox();
		orderAfsluitingBestelling = new OrderAfsluitingBestelling();
		orderNieuweBestelling= new OrderNieuweBestelling(controller);
		orderOptions = new OrderOptions(controller);
		orderBroodjes = new OrderBroodjes(controller);
		stage.setTitle("ORDER VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);
		Scene scene = new Scene(root, 650, 650);
		stage.sizeToScene();
		stage.setScene(scene);
		root.getChildren().addAll(orderNieuweBestelling,orderOptions,orderBroodjes,orderAfsluitingBestelling);
		stage.show();
	}

	public void refresh() {
		this.orderNieuweBestelling.update();
		this.orderOptions.update();
		this.orderBroodjes.update();
		this.orderAfsluitingBestelling.update();
	}

	public void updateBestellijnen(List<Bestellijn> lijstBestellijnen) {
		this.orderBroodjes.updateBestellijnen(lijstBestellijnen);
	}

	public void updateStatusBroodjesKnoppen(Map<String, Integer> voorraadBroodjes) {
		this.orderOptions.updateStatusBroodjesKnoppen(voorraadBroodjes);
	}

	public void updateStatusBelegKnoppen(Map<String, Integer> voorraadBeleg) {
		this.orderOptions.updateStatusBelegKnoppen(voorraadBeleg);
	}
}
