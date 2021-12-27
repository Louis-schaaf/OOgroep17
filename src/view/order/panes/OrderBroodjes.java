package view.order.panes;

import controller.BestelViewController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.BelegSoort;
import model.Bestellijn;
import model.Broodje;

import java.util.List;

public class OrderBroodjes extends GridPane {
    public BestelViewController controller;

    public OrderBroodjes(BestelViewController controller) {
        this.controller = controller;
        this.setPadding(new Insets(10, 0, 10, 20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.add(new Text("Aantal Broodjes: "), 0, 0);
        GridPane broodjes = new GridPane();
        GridPane lijnInLijst = new GridPane();
        setBroodjes(broodjes);
        setLijninLijst(lijnInLijst);
        this.add(broodjes,0,1,1,2);
        this.add(lijnInLijst, 1,1);
        Button buttonAnnuleren = new Button("Annuleer Bestelling");
        buttonAnnuleren.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(10), Insets.EMPTY)));
        buttonAnnuleren.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        this.add(buttonAnnuleren, 0 , 3);
    }
    private void setLijninLijst(GridPane pane){
        pane.setPadding(new Insets(10));
        pane.add(new Text("Selecteer lijn in Lijst:"), 0,0);
        pane.setVgap(30); //vertical gap in pixels
        pane.add(fixButtonColorBorder(new Button("Voeg hetzelfde broodje toe")),0, 1);
        pane.setVgap(10);
        pane.add(fixButtonColorBorder(new Button("Verwijder broodje")),0, 2);
        pane.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(10), Insets.EMPTY)));

    }
    private void setBroodjes(GridPane broodjes) {
        TableView<Broodje> table = new TableView<>();
        broodjes.setPadding(new Insets(5, 5, 5, 5));
        broodjes.setVgap(5);
        broodjes.setHgap(5);
        TableColumn<Broodje, String> firstNameColumn = new TableColumn<Broodje, String>("Broodje");
        TableColumn<Broodje, Double> secondNameColumn = new TableColumn<Broodje, Double>("Beleg");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Broodje, String>("name"));
        secondNameColumn.setCellValueFactory(new PropertyValueFactory<Broodje, Double>("beleg"));
        table.getColumns().addAll(firstNameColumn,secondNameColumn);
        broodjes.add(table, 0, 1);
    }

    private Button fixButtonColorBorder(Button button){
        button.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        return button;
    }

    public void update() {
    }

    public void updateBestellijnen(List<Bestellijn> lijstBestellijnen) {
    }
}
