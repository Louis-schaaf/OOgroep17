package view.order.panes;

import controller.BestelViewController;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
import jxl.read.biff.BiffException;

import java.io.IOException;

public class OrderNieuweBestelling extends GridPane {
    public BestelViewController controller;

    public OrderNieuweBestelling(BestelViewController controller){
        this.controller = controller;
        this.setPadding(new Insets(10,0,10,20));
        ChoiceBox<String> chbx  = new ChoiceBox<>();
        chbx.getItems().addAll("Goedkoopste broodje gratis", "Louis",
                "Jarne", "Jasper");
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.add(this.setUpOrderButton(),0, 0);
        this.add(new Text("Volgnr: 1"), 1,0);
        this.setHgap(60);
        this.add(chbx,3,0);
    }

    private Button setUpOrderButton() {
        Button button = new Button();
        button.setText("Nieuwe bestelling");

        button.setOnAction(event -> {
            button.setDisable(true);
        });
        return button;
    }

    public void update() {
    }
}
