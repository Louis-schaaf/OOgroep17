package view.order.panes;

import controller.BestelViewController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import jxl.read.biff.BiffException;
import model.Bestelling;

import java.io.IOException;

public class OrderNieuweBestelling extends GridPane {
    public BestelViewController controller;
    ChoiceBox choiceBox;
    Button buttonNieuweBestelling;
    Text volgnummerText;
    int volgnummer = 1;


    public OrderNieuweBestelling(BestelViewController controller){
        choiceBox = new ChoiceBox<>();
        buttonNieuweBestelling = new Button();
        this.controller = controller;
        this.setPadding(new Insets(10,0,10,20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.add(this.setUpOrderButton(),0, 0);
        this.add(this.setUpVolgnummer(), 1,0);
        this.add(this.setUpChoiceBox(),3,0);
        this.setHgap(60);
    }

    // Zet een choicebox op en zet deze als standaardwaarde op disabled.
    private Node setUpChoiceBox() {
        choiceBox.getItems().addAll("Goedkoopste broodje gratis", "Geen korting", "10% op alles");
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
                if (this.controller.getBestelling().getState().getClass().getName().contains("InWacht")){
                    this.controller.startNieuweBestelling();
                    this.volgnummer++;
                }
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return buttonNieuweBestelling;
    }

    private Text setUpVolgnummer() {
        volgnummerText = new Text("Volgnummer: ");
        volgnummerText.setVisible(false);
        return volgnummerText;
    }

    private void updateVolgnummer() {
        String tekst = this.volgnummerText.getText() + this.volgnummer;
        this.volgnummerText.setText(tekst);
    }

    // De states worden met elkaar vergeleken.
    // Wanneer de state van de bestelling niet meer InWacht staat
    // moet de bestelknop gedisabled worden
    // en de choiceBox worden geanabled.
    public void update(Bestelling bestelling) {
        if (bestelling.getState().getClass().getName().contains("InBestelling")) {
            buttonNieuweBestelling.setDisable(true);
            this.updateVolgnummer();
            volgnummerText.setVisible(true);
            choiceBox.setDisable(false);
        }
    }
    }
