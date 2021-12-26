package view.order.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.lang.reflect.Field;

public class OrderOptions extends GridPane {
    public OrderOptions(){
        this.setPadding(new Insets(10,0,10,20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.add(fixButtonColorBorder(new Button("Maïs"),true,true),1, 0);
        this.add(fixButtonColorBorder(new Button("Wit"),true,true),2, 0);
        this.add(fixButtonColorBorder(new Button("Volkoren"),true,true),3, 0);
        this.setHgap(10);
        this.add(fixButtonColorBorder(new Button("Feta"),false,true),1, 1);
        this.add(fixButtonColorBorder(new Button("Hesp"),false,true),2, 1);
        this.add(fixButtonColorBorder(new Button("Kaas"),false,true),3, 1);
        this.add(fixButtonColorBorder(new Button("Komkommer"),false,true),4, 1);
        this.add(fixButtonColorBorder(new Button("Olijven"),false,true),5, 1);
        this.add(fixButtonColorBorder(new Button("Sla"),false,true),6, 1);
        this.add(fixButtonColorBorder(new Button("Tomaat"),false,true),7, 1);
        this.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(10), Insets.EMPTY)));
    }
    private Button fixButtonColorBorder(Button button, boolean gray, boolean border){
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
