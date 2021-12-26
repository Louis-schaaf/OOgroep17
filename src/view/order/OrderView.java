package view.order;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.order.panes.OrderAfsluitingBestelling;
import view.order.panes.OrderBroodjes;
import view.order.panes.OrderNieuweBestelling;
import view.order.panes.OrderOptions;

public class OrderView {
	private Stage stage = new Stage();
	private OrderAfsluitingBestelling orderAfsluitingBestelling;
	private OrderNieuweBestelling orderNieuweBestelling;
	private OrderOptions orderOptions;
	private OrderBroodjes orderBroodjes;

		
	public OrderView(){
		VBox root = new VBox();
		orderAfsluitingBestelling = new OrderAfsluitingBestelling();
		orderNieuweBestelling= new OrderNieuweBestelling();
		orderOptions = new OrderOptions();
		orderBroodjes = new OrderBroodjes();
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
}
