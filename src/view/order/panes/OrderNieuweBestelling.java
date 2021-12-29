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
import model.Instellingen;

import java.io.IOException;

/**
 * Deze klasse weergeeft de "Nieuwe bestelling"-pane van de Order-view.
 */
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
        this.setHgap(30);
        this.setVgap(10);
        this.add(this.setUpOrderButton(),0, 0);
        this.add(this.setUpVolgnummer(), 1,0);
        this.add(this.setUpChoiceBox(),3,0);
        this.setHgap(60);
    }

    private Node setUpChoiceBox() {
        choiceBox.getItems().addAll("GOEDKOOPSTEGRATIS", "GEENKORTING", "TIENPROCENT");
        choiceBox.setValue(Instellingen.getKorting());
        choiceBox.setDisable(true);
        return choiceBox;
    }

    public String getChoiceBox(){
        return this.choiceBox.getValue().toString();
    }

    private void updateChoiceBox() {
        this.choiceBox.setValue(Instellingen.getKorting());
    }

    private Button setUpOrderButton() {
        buttonNieuweBestelling.setText("Nieuwe bestelling");
        buttonNieuweBestelling.setOnAction(event -> {
            try {
                    this.controller.startNieuweBestelling();
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return buttonNieuweBestelling;
    }

    private Text setUpVolgnummer() {
        volgnummerText = new Text("Volgnummer: 1");
        volgnummerText.setVisible(false);
        return volgnummerText;
    }

    private void updateVolgnummer() {
        String tekst = "Volgnummer: " + this.volgnummer;
        this.volgnummerText.setText(tekst);
        this.controller.getBestelling().setVolgnummer(volgnummer);
    }

    public void hideVolgNummer() {
        this.volgnummerText.setVisible(false);
    }

    public void update(Bestelling bestelling) {
        if (bestelling.getState().getClass().getName().contains("InWacht")) {
            this.updateChoiceBox();
            buttonNieuweBestelling.setDisable(false);
            volgnummerText.setVisible(false);
            choiceBox.setDisable(true);
        }
        if (bestelling.getState().getClass().getName().contains("InBestelling")) {
            buttonNieuweBestelling.setDisable(true);
            this.updateVolgnummer();
            volgnummerText.setVisible(true);
            choiceBox.setDisable(false);
        }
        if (bestelling.getState().getClass().getName().contains("Afgesloten")) {
            buttonNieuweBestelling.setDisable(true);
            choiceBox.setDisable(true);
        }
        if (bestelling.getState().getClass().getName().contains("Betaald")) {
            choiceBox.setDisable(true);
            this.volgnummer++;
        }
        if (bestelling.getState().getClass().getName().contains("InKeuken")) {
            buttonNieuweBestelling.setDisable(false);
        }
    }
}
