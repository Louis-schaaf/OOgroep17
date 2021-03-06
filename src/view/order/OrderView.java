package view.order;

import controller.BestelViewController;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Bestellijn;
import model.Bestelling;
import view.order.panes.OrderAfsluitingBestelling;
import view.order.panes.OrderBroodjes;
import view.order.panes.OrderNieuweBestelling;
import view.order.panes.OrderOptions;

import java.util.Map;

/**
 * Deze klasse maakt de Order-view aan.
 */
public class OrderView {
	private Stage stage = new Stage();
	private Stage popup;
	private OrderAfsluitingBestelling orderAfsluitingBestelling;
	private OrderNieuweBestelling orderNieuweBestelling;
	private OrderOptions orderOptions;
	private OrderBroodjes orderBroodjes;
		
	public OrderView(BestelViewController controller){
		VBox root = new VBox();
		orderAfsluitingBestelling = new OrderAfsluitingBestelling(controller);
		orderOptions = new OrderOptions(controller);
		orderBroodjes = new OrderBroodjes(controller);
		orderNieuweBestelling = new OrderNieuweBestelling(controller);
		stage.setTitle("ORDER VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);
		Scene scene = new Scene(root, 650, 650);
		stage.sizeToScene();
		stage.setScene(scene);
		root.getChildren().addAll(orderNieuweBestelling,orderOptions,orderBroodjes,orderAfsluitingBestelling);
		stage.show();
		this.setUpPopup();
		controller.setOrderView(this);
	}

	public void setUpPopup() {
		popup = new Stage();
		popup.setTitle("Error");
		VBox comp = new VBox();
		Label label = new Label("U heeft geen broodje gekozen of de voorraad is leeg.");
		comp.getChildren().add(label);
		Scene stageScene = new Scene(comp, 360, 30);
		popup.setScene(stageScene);
		popup.hide();
	}

	public void toonError() {
		this.popup.show();
	}

	public Bestellijn getSelected() {
		return orderBroodjes.getSelectedBestellijn();
	}

	public void update(Bestelling bestelling) {
		this.orderNieuweBestelling.update(bestelling);
		this.orderOptions.update(bestelling);
		this.orderBroodjes.update(bestelling);
		this.orderAfsluitingBestelling.update(bestelling);
	}

	public void updateBestellijnen() {
		this.orderBroodjes.updateBestellijnen();
	}

	public void updateStatusBroodjesKnoppen(Map<String, Integer> voorraadBroodjes) {
		this.orderOptions.updateStatusBroodjesKnoppen(voorraadBroodjes);
	}

	public void updateStatusBelegKnoppen(Map<String, Integer> voorraadBeleg) {
		this.orderOptions.updateStatusBelegKnoppen(voorraadBeleg);
	}

	public void hideVolgNummer() {
		this.orderNieuweBestelling.hideVolgNummer();
	}

	public void resetAantalBroodjes() {
		this.orderBroodjes.resetAantalBroodjes();
	}

	public void updateBedrag(double bedrag) {
		this.orderAfsluitingBestelling.updateBedrag(bedrag);
	}

	public String getKorting(){
		return this.orderNieuweBestelling.getChoiceBox();
	}
}
