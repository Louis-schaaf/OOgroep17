package view.order.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.BelegSoort;
import model.Broodje;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.lang.reflect.Field;
import java.util.Map;

public class OrderOptions extends GridPane {
    public OrderOptions(){
        this.setPadding(new Insets(10,0,10,20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        Map<String, Broodje> broodjes = LoadSaveStrategyFactory.createLoadSaveStrategy("EXCELBROODJES").load();
        Map<String, BelegSoort> beleg = LoadSaveStrategyFactory.createLoadSaveStrategy("EXCELBELEG").load();
        int broodjesIndex = 0;
        int belegIndex = 0;
        for (Map.Entry<String, Broodje> entry : broodjes.entrySet()) {;
            this.add(fixButtonColorBorder(entry.getKey(), true, true),broodjesIndex, 0);
            broodjesIndex++;
        }
        this.setHgap(10);
        for (Map.Entry<String, BelegSoort> entry : beleg.entrySet()) {;
            this.add(fixButtonColorBorder(entry.getKey(), false, true), belegIndex, 1);
            belegIndex++;
        }
        this.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(10), Insets.EMPTY)));
    }
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
        return button;
    }
}
