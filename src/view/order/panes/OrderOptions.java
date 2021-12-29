package view.order.panes;

import controller.BestelViewController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jxl.read.biff.BiffException;
import model.BelegSoort;
import model.Broodje;
import model.Bestelling;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Deze klasse weergeeft de "Bestel opties"-pane van de Order-view.
 */
public class OrderOptions extends GridPane {
    public BestelViewController controller;
    ArrayList<Button> buttonsBroodjes = new ArrayList<>();
    ArrayList<Button> buttonsBeleg = new ArrayList<>();
    int broodjesIndex = 0;
    int belegIndex = 0;

    public OrderOptions(BestelViewController controller){
        this.controller = controller;
        this.setPadding(new Insets(10,0,10,20));
        this.setHgap(30);
        this.setVgap(10);
        this.setHgap(10);
        this.setUpBroodjesKnoppen();
        this.setUpBelegKnoppen();
        this.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(10), Insets.EMPTY)));
    }

    private void setUpBroodjesKnoppen() {
        Map<String, Broodje> broodjes = this.controller.getBroodjesDB();

        for (Map.Entry<String, Broodje> entry : broodjes.entrySet()) {
            if (entry.getValue().getActualStock() > 0) {
                BroodjesKnoppen(entry.getKey(), true, true);
            }
        }
    }

    private Button BroodjesKnoppen(String buttonName, boolean gray, boolean border){
        Button button = new Button(buttonName);
        if (gray == true){
            button.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        }else{
            button.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(10), Insets.EMPTY)));
        }
        if (border == true){
            button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        }
        button.setDisable(true);
        buttonsBroodjes.add(button);
        button.setOnAction(e -> {
                try {
                    controller.voegBestellijnToe(buttonName);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (BiffException biffException) {
                    biffException.printStackTrace();
                }
        });
        this.add(button,broodjesIndex, 0);
        broodjesIndex++;
        return button;
    }

    private void setUpBelegKnoppen() {
        Map<String, BelegSoort> beleg = this.controller.getBelegDB();

        for (Map.Entry<String, BelegSoort> entry : beleg.entrySet()) {
            if (entry.getValue().getActualStock() > 0) {
                BelegKnoppen(controller.getBestelling(), entry.getKey(), false, true);
            }
        }
    }

    private Button BelegKnoppen(Bestelling bestelling, String buttonName, boolean gray, boolean border){
        Button button = new Button(buttonName);
        if (gray == true){
            button.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        }else{
            button.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(10), Insets.EMPTY)));
        }
        if (border == true){
            button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        }
        button.setDisable(true);
        buttonsBeleg.add(button);
        button.setOnAction(e -> {
                try {
                    controller.voegBelegToe(buttonName, bestelling.getBestellijnen().indexOf(controller.getBestellijn()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (BiffException biffException) {
                    biffException.printStackTrace();
                }
        });
        this.add(button,belegIndex, 1);
        belegIndex++;
        return button;
    }

    public void updateStatusBroodjesKnoppen(Map<String, Integer> voorraadBroodjes) {
        for (Button b : this.buttonsBroodjes) {
            if (voorraadBroodjes.containsKey(b.getText())) {
                int i = voorraadBroodjes.get(b.getText());
                if (i < 1) {
                    b.setDisable(true);
                }else{
                    b.setDisable(false);
                }
            }
        }
    }

    public void updateStatusBelegKnoppen(Map<String, Integer> voorraadBeleg) {
        Button[] buttons = this.getManagedChildren().toArray(new Button[0]);
        for (Button b : buttons) {
            if (voorraadBeleg.containsKey(b.getText())) {
                int i = voorraadBeleg.get(b.getText());
                if (i < 1) {
                    b.setDisable(true);
                }else{
                    b.setDisable(false);
                }
            }
        }
    }

    public void update(Bestelling bestelling) {
        if (bestelling.getState().getClass().getName().contains("InWacht")) {

            for (Button b : this.buttonsBroodjes) {
                b.setDisable(true);
            }
            for (Button b : this.buttonsBeleg){
                b.setDisable(true);
            }
        }
        if (bestelling.getState().getClass().getName().contains("InBestelling")) {
            for (Button b : this.buttonsBroodjes) {
                b.setDisable(false);
            }
            for (Button b : this.buttonsBeleg){
                b.setDisable(false);
            }
        }
        if (bestelling.getState().getClass().getName().contains("Afgesloten")) {
            for (Button b : this.buttonsBroodjes) {
                b.setDisable(true);
            }
            for (Button b : this.buttonsBeleg){
                b.setDisable(true);
            }
        }
        if (bestelling.getState().getClass().getName().contains("Betaald")) {
            for (Button b : this.buttonsBroodjes) {
                b.setDisable(true);
            }
            for (Button b : this.buttonsBeleg){
                b.setDisable(true);
            }
        }
    }
}
