package view.kitchen;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KitchenView {
	
	private Stage stage = new Stage();		
	
	public KitchenView(){			
		stage.setTitle("KITCHEN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(680);
		stage.setY(470);
		Group root = new Group();
		Scene scene = new Scene(root, 1000, 200);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
