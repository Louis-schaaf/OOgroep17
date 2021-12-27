package view.order.panes;

import controller.BestelViewController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.BelegSoort;
import model.Broodje;
import model.bestelling.Bestelling;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderOptions extends GridPane {
    public BestelViewController controller;
    //TODO
    //Check Observer Style methode toepassen! (Story 3.3)

    ArrayList<Button> buttons = new ArrayList<>();
    public OrderOptions(BestelViewController controller){
        this.controller = controller;
        this.setPadding(new Insets(10,0,10,20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels

        //Map<String, Broodje> broodjes = LoadSaveStrategyFactory.createLoadSaveStrategy("EXCELBROODJES").load();
        //Map<String, BelegSoort> beleg = LoadSaveStrategyFactory.createLoadSaveStrategy("EXCELBELEG").load();
        Map<String, Broodje> broodjes = LoadSaveStrategyFactory.createLoadSaveStrategy("TEKSTBROODJES").load();
        Map<String, BelegSoort> beleg = LoadSaveStrategyFactory.createLoadSaveStrategy("TEKSTBELEG").load();

        int broodjesIndex = 0;
        int belegIndex = 0;

        for (Map.Entry<String, Broodje> entry : broodjes.entrySet()) {
            if (entry.getValue().getActualStock() > 0) {
                this.add(fixButtonColorBorder(entry.getKey(), true, true), broodjesIndex, 0);
                broodjesIndex++;
            }
        }
        this.setHgap(10);
        for (Map.Entry<String, BelegSoort> entry : beleg.entrySet()) {
            if (entry.getValue().getActualStock() > 0) {
                this.add(fixButtonColorBorder(entry.getKey(), false, true), belegIndex, 1);
                belegIndex++;
            }
        }
        this.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(10), Insets.EMPTY)));
    }

    // Geeft een knop terug:
    // a) buttonName: De naam van de knop
    // b) gray: Indien true wordt de achtergrond van de knop grijs en anders geel
    // c) border: Indien true wordt er een zwarte border getoond rond de knop.
    private Button fixButtonColorBorder(String buttonName, boolean gray, boolean border){
        Button button = new Button(buttonName);
        if (gray == true){
            button.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        }else{
            button.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(10), Insets.EMPTY)));
        }
        if (border == true){
            button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        }
        buttons.add(button);
        button.setDisable(true);
        return button;
    }

    //Zet alle knoppen van deze pane in een array
    //Indien de voorraad van de broodjes de tekst van de knop contains
    //Dan kijkt die als de voorraad kleiner is dan 0; als dit is dan wordt de knop uitgezet.
    public void updateStatusBroodjesKnoppen(Map<String, Integer> voorraadBroodjes) {
        Button[] buttons = this.getManagedChildren().toArray(new Button[0]);
        for (Button b : buttons) {
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
                    b.setDisable(true);
                }
            }
        }
    }

    // Zet alle nodes uit van deze gridpane.
    public void disableAll() {
        List<Node> nodes = this.getManagedChildren();
        for (Node n : nodes) {
            n.setDisable(true);
        }
    }
    public void update(Bestelling bestelling) {
        //TODO De nodes op deze pagina's per state van bestelling apart aanspreken en updaten. Dit lijkt me het makkelijkst
        // te doen door de Nodes als instantievariabelen van deze klasse te maken. Voor de moment worden alle nodes die niet
        // nodig zijn gedisabled met de disableAll() hierboven.
    }
}
