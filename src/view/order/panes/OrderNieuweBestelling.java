package view.order.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class OrderNieuweBestelling extends GridPane {
    public OrderNieuweBestelling(){
        this.setPadding(new Insets(10,0,10,20));
        ChoiceBox<String> chbx  = new ChoiceBox<>();
        chbx.getItems().addAll("Goedkoopste broodje gratis", "Louis",
                "Jarne", "Jasper");
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.add(new Button("Nieuwe Bestelling"),0, 0);
        this.add(new Text("Volgnr: "), 1,0);
        this.setHgap(60);
        this.add(chbx,3,0);
    }

}
