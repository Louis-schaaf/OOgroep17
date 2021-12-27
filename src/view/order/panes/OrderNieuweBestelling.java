package view.order.panes;

import com.sun.org.apache.xpath.internal.operations.Or;
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
    Button buttonNieuweBestelling;


    public OrderNieuweBestelling(BestelViewController controller){
        choiceBox = new ChoiceBox<>();
        buttonNieuweBestelling = new Button();
        this.controller = controller;
        this.setPadding(new Insets(10,0,10,20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.add(this.setUpOrderButton(),0, 0);
        this.add(new Text("Volgnummer: 1"), 1,0);
        this.add(this.setUpChoiceBox(),3,0);
        this.setHgap(60);
    }

    // Zet een choicebox op en zet deze als standaardwaarde op disabled.
    private Node setUpChoiceBox() {
        choiceBox.getItems().addAll("Goedkoopste broodje gratis", "Louis", "Jarne", "Jasper");
        choiceBox.setDisable(true);
        return choiceBox;
    }

    // Zet een orderButton van "Nieuwe Bestelling"
    // Wanneer deze wordt ingedrukt wordt er een nieuwe bestelling aangemaakt.
    // De knop wort daarna teruggegeven.
    private Button setUpOrderButton() {
        buttonNieuweBestelling.setText("Nieuwe bestelling");
        buttonNieuweBestelling.setOnAction(event -> {
            try {
                this.controller.startNieuweBestelling();
                buttonNieuweBestelling.setDisable(true);
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return buttonNieuweBestelling;
    }

    // De states worden met elkaar vergeleken.
    // Wanneer de state van de bestelling niet meer InWacht staat
    // moet de bestelknop gedisabled worden
    // en de choiceBox worden geanabled.
    public void update(Bestelling bestelling) {
        /*System.out.println(bestelling.getState());
        System.out.println(bestelling.getInWacht());
        if (bestelling.getState() != bestelling.getInWacht()){
            choiceBox.setDisable(false);
            buttonNieuweBestelling.setDisable(true);
        }else{
            choiceBox.setDisable(true);
            buttonNieuweBestelling.setDisable(false);
        }*/
    }
}
