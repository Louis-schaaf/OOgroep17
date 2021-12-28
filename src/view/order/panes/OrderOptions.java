package view.order.panes;

import controller.BestelViewController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jxl.read.biff.BiffException;
import model.BelegSoort;
import model.Broodje;
import model.Bestelling;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderOptions extends GridPane {
    public BestelViewController controller;
    int broodjesIndex = 0;
    int belegIndex = 0;
    //TODO
    //Check Observer Style methode toepassen! (Story 3.3)

    ArrayList<Button> buttonsBroodjes = new ArrayList<>();
    ArrayList<Button> buttonsBeleg = new ArrayList<>();
    public OrderOptions(BestelViewController controller){
        this.controller = controller;
        this.setPadding(new Insets(10,0,10,20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels

        Map<String, Broodje> broodjes = LoadSaveStrategyFactory.createLoadSaveStrategy("EXCELBROODJES").load();
        Map<String, BelegSoort> beleg = LoadSaveStrategyFactory.createLoadSaveStrategy("EXCELBELEG").load();
        //Map<String, Broodje> broodjes = LoadSaveStrategyFactory.createLoadSaveStrategy("TEKSTBROODJES").load();
        //Map<String, BelegSoort> beleg = LoadSaveStrategyFactory.createLoadSaveStrategy("TEKSTBELEG").load();

        this.setHgap(10);
        for (Map.Entry<String, Broodje> entry : broodjes.entrySet()) {
            if (entry.getValue().getActualStock() > 0) {
                BroodjesKnoppen(controller.getBestelling(), entry.getKey(), true, true);
            }
        }
        for (Map.Entry<String, BelegSoort> entry : beleg.entrySet()) {
            if (entry.getValue().getActualStock() > 0) {
                BelegKnoppen(controller.getBestelling(), entry.getKey(), false, true);
            }
        }
        this.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(10), Insets.EMPTY)));
    }

    // Geeft een knop terug:
    // a) buttonName: De naam van de knop
    // b) gray: Indien true wordt de achtergrond van de knop grijs en anders geel
    // c) border: Indien true wordt er een zwarte border getoond rond de knop.
    private Button BroodjesKnoppen(Bestelling bestelling, String buttonName, boolean gray, boolean border){
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
            if (bestelling.getState().getClass().getName().contains("InBestelling")) {
                try {
                    controller.voegBestellijnToe(buttonName);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (BiffException biffException) {
                    biffException.printStackTrace();
                }
            }
        });
        this.add(button,broodjesIndex, 0);
        broodjesIndex++;
        return button;
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
            if (bestelling.getState().getClass().getName().contains("InBestelling")) {
                try {
                    controller.voegBelegToe(buttonName, 0);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (BiffException biffException) {
                    biffException.printStackTrace();
                }
            }
        });
        this.add(button,belegIndex, 1);
        belegIndex++;
        return button;
    }

    //Zet alle knoppen van deze pane in een array
    //Indien de voorraad van de broodjes de tekst van de knop contains
    //Dan kijkt die als de voorraad kleiner is dan 0; als dit is dan wordt de knop uitgezet.
    public void updateStatusBroodjesKnoppen(Map<String, Integer> voorraadBroodjes) {
        for (Button b : this.buttonsBroodjes) {
            if (voorraadBroodjes.containsKey(b.getText())) {
                int i = voorraadBroodjes.get(b.getText());
                if (i < 1) {
                    b.setDisable(true);
                }
            }
        }
    }

    //Zet alle knoppen van deze pane in een array
    //Indien de voorraad van de beleg de tekst van de knop contains
    //Dan kijkt die als de voorraad kleiner is dan 0; als dit is dan wordt de knop uitgezet.
    public void updateStatusBelegKnoppen(Map<String, Integer> voorraadBeleg) {
        Button[] buttons = this.getManagedChildren().toArray(new Button[0]);
        for (Button b : buttons) {
            if (voorraadBeleg.containsKey(b.getText())) {
                int i = voorraadBeleg.get(b.getText());
                if (i < 1) {
                    b.setVisible(false);
                }
            }
        }
    }

    public void drukBroodjesKnop(){
    }

    // Zet alle nodes uit van deze gridpane.
    public void disableAll() {
        List<Node> nodes = this.getManagedChildren();
        for (Node n : nodes) {
            n.setVisible(false);
        }
    }

    public void enableAll() {
        List<Node> nodes = this.getManagedChildren();
        for (Node n : nodes) {
            n.setDisable(false);
        }
    }

    public void update(Bestelling bestelling) {
        if (bestelling.getState().getClass().getName().contains("InBestelling")) {
            for (Button b : this.buttonsBroodjes) {
                b.setDisable(false);
            }
            for (Button b : this.buttonsBeleg){
                b.setDisable(false);
            }
        }

    }
}
