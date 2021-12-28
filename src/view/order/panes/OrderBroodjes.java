package view.order.panes;

import controller.BestelViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Bestellijn;
import model.Bestelling;

import java.util.List;

public class OrderBroodjes extends GridPane {
    public BestelViewController controller;
    GridPane broodjes = new GridPane();
    GridPane lijnInLijst = new GridPane();
    public List<Bestellijn> lijstBestellijnen;
    TableView<Bestellijn> table;

    public OrderBroodjes(BestelViewController controller) {
        this.controller = controller;
        this.setPadding(new Insets(10, 0, 10, 20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.add(new Text("Aantal Broodjes: "), 0, 0);
        setBroodjes(broodjes);
        setLijninLijst(lijnInLijst);
        this.add(broodjes, 0, 1, 1, 2);
        this.add(lijnInLijst, 1, 1);
        this.add(annuleerKnop(), 0, 3);
    }

    // Maak een Gridpane voor LijninLijst
    // Hiervoor maken we een tekst "Selecteer lijn in lijst:"
    // Daarna voegen we nieuwe knoppen toe:
    //  a) Voeg hetzelfde broodje toe
    //  b) Verwijder broodje
    // Maak de achtergrond van de LijninLijst Blauwachtig
    private void setLijninLijst(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.add(new Text("Selecteer lijn in Lijst:"), 0, 0);
        pane.setVgap(30); //vertical gap in pixels
        pane.add(fixButtonColorBorder(new Button("Voeg hetzelfde broodje toe")), 0, 1);
        pane.setVgap(10);
        pane.add(fixButtonColorBorder(new Button("Verwijder broodje")), 0, 2);
        pane.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(10), Insets.EMPTY)));

    }

    // Maak een Gridpane voor Broodjes
    // Hiervoor maken we een Table met 2 kolommen:
    //  a) Broodje
    //  b) Beleg
    // In de onderstaande tabel zou de naam van het broodje komen te staan
    // En in de andere tabel komen alle belegsoorten van het bijhorende broodje.
    private void setBroodjes(GridPane broodjes) {
        broodjes.setPadding(new Insets(5, 5, 5, 5));
        broodjes.setVgap(5);
        broodjes.setHgap(5);
        table = new TableView<>();
        ObservableList<Bestellijn> observableList = FXCollections.observableList(controller.getLijstBestellijnen());
        table.setItems(observableList);
        TableColumn<Bestellijn, String> firstNameColumn = new TableColumn<>("Broodje");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Bestellijn, String>("NaamBroodje"));
        //firstNameColumn.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getNaamBroodje()));
        table.getColumns().add(firstNameColumn);

        //firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("naamBroodje"));
        System.out.println(table.getColumns());
        broodjes.add(table, 0, 1);
    }

    // Geef een knop in en pas deze aan en deze wordt daarna teruggegeven
    // Maak de achtegrond van de knop lichtgrijs
    // Maak de border van de knop zwart
    private Button fixButtonColorBorder(Button button) {
        button.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        return button;
    }

    // Alle nodes van deze Gridpane worden gedisabled.
    public void disableAll() {
        List<Node> nodes = this.getManagedChildren();
        for (Node n : nodes) {
            n.setDisable(true);
        }
    }

    // Maak een annuleerKnop aan met "Annuleer Bestelling"
    // Deze krijgt een rode achtergrond met een zwarte border.
    public Button annuleerKnop() {
        Button buttonAnnuleren = new Button("Annuleer Bestelling");
        buttonAnnuleren.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(10), Insets.EMPTY)));
        buttonAnnuleren.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        return buttonAnnuleren;
    }

    public void update(Bestelling bestelling) {
        //TODO De nodes op deze pagina's per state van bestelling apart aanspreken en updaten. Dit lijkt me het makkelijkst
        // te doen door de Nodes als instantievariabelen van deze klasse te maken. Voor de moment worden alle nodes die niet
        // nodig zijn gedisabled met de disableAll() hierboven.
    }

    public void updateBestellijnen() {
        /*table.getItems().clear();
        table.refresh();
        table.getColumns().get(0).setVisible(false);
        table.getColumns().get(0).setVisible(true);*/
        //TODO: er worden 2 vensters aangemaakt
        ObservableList<Bestellijn> bestellijnObservableList = FXCollections.observableList(controller.getLijstBestellijnen());
        table.setItems(bestellijnObservableList);
        table.getColumns().get(0).setVisible(false);
        table.getColumns().get(0).setVisible(true);
        for (int i = 0; i < table.getItems().size(); i++) {
            System.out.println(table.getItems().get(i).naamBroodje);
        }
        table.refresh();
        broodjes.getChildren().remove(table);
        setBroodjes(broodjes);
        table.setVisible(false);
        table.setVisible(true);
    }
}
