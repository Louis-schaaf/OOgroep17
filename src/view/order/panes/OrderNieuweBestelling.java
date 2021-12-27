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
import model.bestelling.Bestelling;
import model.bestelling.states.InWacht;

import java.io.IOException;

public class OrderNieuweBestelling extends GridPane {
    public BestelViewController controller;
    ChoiceBox choiceBox;


    public OrderNieuweBestelling(BestelViewController controller){
        this.controller = controller;
        this.setPadding(new Insets(10,0,10,20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.add(this.setUpOrderButton(),0, 0);
        this.add(new Text("Volgnummer: 1"), 1,0);
        this.setHgap(60);
        this.add(this.setUpChoiceBox(),3,0);
    }

    private Node setUpChoiceBox() {
        ChoiceBox<String> choiceBox  = new ChoiceBox<>();
        choiceBox.getItems().addAll("Goedkoopste broodje gratis", "Louis",
                "Jarne", "Jasper");
        if (this.controller.getState().getClass() == InWacht.class) {
            choiceBox.setDisable(true);
        } else {
            choiceBox.setDisable(false);
        }
        return choiceBox;
    }

    private Button setUpOrderButton() {
        Button button = new Button();
        button.setText("Nieuwe bestelling");

        button.setOnAction(event -> {
            try {
                this.controller.startNieuweBestelling();
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            button.setDisable(true);
        });
        return button;
    }

    public void update(Bestelling bestelling) {
        //TODO De nodes op deze pagina's per state van bestelling apart aanspreken en updaten. Dit lijkt me het makkelijkst
        // te doen door de Nodes als instantievariabelen van deze klasse te maken. Voor de moment worden alle nodes die niet
        // nodig zijn gedisabled met de disableAll() hierboven.
    }
}
