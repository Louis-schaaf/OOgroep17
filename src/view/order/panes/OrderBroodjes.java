package view.order.panes;

import com.sun.rowset.internal.Row;
import controller.BestelViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import jxl.read.biff.BiffException;
import model.BelegSoort;
import model.Bestellijn;
import model.Bestelling;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class OrderBroodjes extends GridPane {
    public BestelViewController controller;
    GridPane broodjes = new GridPane();
    GridPane lijnInLijst = new GridPane();
    public List<Bestellijn> lijstBestellijnen;
    TableView<Bestellijn> table;
    Text aantalBroodjesTekst;
    int aantalBroodjes = 1;
    Button identiek;
    Button verwijder;
    Button annuleer;
    Bestellijn selectedBestellijn;

    public OrderBroodjes(BestelViewController controller) {
        this.controller = controller;
        this.setPadding(new Insets(10, 0, 10, 20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.add(this.setUpAantalBroodjes(), 0, 0);
        setBroodjes(broodjes);
        setLijninLijst(lijnInLijst);
        this.add(broodjes, 0, 1, 1, 2);
        this.add(lijnInLijst, 1, 1);
        this.add(annuleerKnop(), 0, 3);
    }

    private Text setUpAantalBroodjes() {
        aantalBroodjesTekst = new Text("Aantal broodjes: 0");
        aantalBroodjesTekst.setVisible(true);
        return aantalBroodjesTekst;
    }

    private void updateAantalBroodjes() {
        this.aantalBroodjes = table.getItems().size();
        String tekst = "Aantal broodjes: " + this.aantalBroodjes;
        this.aantalBroodjesTekst.setText(tekst);
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
        pane.add(fixButtonColorBorder(setUpIdentiekeKnop()), 0, 1);
        pane.setVgap(10);
        pane.add(fixButtonColorBorder(setUpVerwijderKnop()), 0, 2);
        pane.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(10), Insets.EMPTY)));

    }

    private Button setUpVerwijderKnop() {
        this.verwijder = new Button("Verwijder broodje");
        this.verwijder.setDisable(true);
        return this.verwijder;
    }

    private Button setUpIdentiekeKnop() {
        this.identiek = new Button("Voeg hetzelfde broodje toe");
        this.identiek.setDisable(true);
        return this.identiek;
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
        table.setRowFactory(tv -> {
            TableRow<Bestellijn> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Bestellijn bestellijn = row.getItem();
                selectedBestellijn = bestellijn;
            });
            return row;
        });
        ObservableList<Bestellijn> observableList = FXCollections.observableList(controller.getLijstBestellijnen());
        table.setItems(observableList);
        TableColumn<Bestellijn, String> firstNameColumn = new TableColumn<>("Broodje");
        TableColumn<Bestellijn, String> secondNameColumn = new TableColumn<>("Beleg");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Bestellijn, String>("NaamBroodje"));
        secondNameColumn.setCellValueFactory(new PropertyValueFactory<Bestellijn, String>("NamenBeleg"));
        //firstNameColumn.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getNaamBroodje()));
        table.getColumns().addAll(firstNameColumn, secondNameColumn);

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

    // Maak een annuleerKnop aan met "Annuleer Bestelling"
    // Deze krijgt een rode achtergrond met een zwarte border.
    public Button annuleerKnop() {
        this.annuleer = new Button("Annuleer Bestelling");
        this.annuleer.setDisable(true);
        this.annuleer.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(10), Insets.EMPTY)));
        this.annuleer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        return this.annuleer;
    }

    public void update(Bestelling bestelling) {
        if (bestelling.getState().getClass().getName().contains("InWacht")) {
            this.annuleer.setDisable(true);
            this.verwijder.setDisable(true);
            this.identiek.setDisable(true);
        }
        if (bestelling.getState().getClass().getName().contains("InBestelling")) {
            this.annuleer.setDisable(false);
            this.verwijder.setDisable(false);
            this.identiek.setDisable(false);

        }
        if (bestelling.getState().getClass().getName().contains("Afgesloten")) {
            this.annuleer.setDisable(false);
            this.verwijder.setDisable(true);
            this.identiek.setDisable(true);

        }
        if (bestelling.getState().getClass().getName().contains("Betaald")) {
            this.annuleer.setDisable(true);
            this.verwijder.setDisable(true);
            this.identiek.setDisable(true);
        }
    }

    public Bestellijn getSelectedBestellijn() {
        return selectedBestellijn;
    }

    public void updateBestellijnen() {
        /*table.getItems().clear();
        table.refresh();
        table.getColumns().get(0).setVisible(false);
        table.getColumns().get(0).setVisible(true);*/
        ObservableList<Bestellijn> bestellijnObservableList = FXCollections.observableList(controller.getLijstBestellijnen());

        /*Bestellijn bestellijn = new Bestellijn("test");
        bestellijn.addNaamBeleg(new BelegSoort("testbeleg",2.0,2,2));
        bestellijn.addNaamBeleg(new BelegSoort("hesp",2.0,2,2));
        bestellijnObservableList.add(bestellijn);*/
        table.setItems(bestellijnObservableList);
        table.getColumns().get(0).setVisible(false);
        table.getColumns().get(0).setVisible(true);
        table.refresh();
        broodjes.getChildren().remove(table);
        setBroodjes(broodjes);
        table.setVisible(false);
        table.setVisible(true);
        this.updateAantalBroodjes();
    }
}
