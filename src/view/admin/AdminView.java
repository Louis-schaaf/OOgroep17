package view.admin;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.BestelFacade;

/**
 * Deze klasse maakt de Admin-view aan.
 */
public class AdminView {
	private Stage stage = new Stage();		
		
	public AdminView(BestelFacade bestelFacade){
		stage.setTitle("ADMIN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(680);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 1000, 400);
		BorderPane borderPane = new AdminMainPane(bestelFacade);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
