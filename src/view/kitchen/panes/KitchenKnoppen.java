package view.kitchen.panes;

import controller.KeukenViewController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Bestellijn;
import model.Bestelling;

import javax.swing.*;
import java.util.ArrayList;

public class KitchenKnoppen extends GridPane {
    public KeukenViewController controller;
    private int counter;
    Text aantalBestellingen;
    Text bestellingInfo;
    Button volgendeBestelling;
    Button bestellingAfgewerkt;
    public KitchenKnoppen(KeukenViewController controller) {
        volgendeBestelling = new Button();
        bestellingAfgewerkt = new Button();
        this.controller = controller;
        this.setPadding(new Insets(10,0,10,20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.add(this.setUpVolgendeBestellingButton(),0,0);
        this.add(this.setUpBestellingAfgewerkt(),1,0);
        this.setHgap(60);
        this.add(this.setUpAantalBestellingen(),0,1);
        this.setHgap(60);
        this.add(this.setUpBestellingInfo(),0,2);
    }

    private Button setUpVolgendeBestellingButton() {
        volgendeBestelling.setText("Volgende bestelling");
        volgendeBestelling.setOnAction(event -> {
            try {
                // this.controller volgende bestelling methode
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return volgendeBestelling;
    }

    private Button setUpBestellingAfgewerkt() {
        bestellingAfgewerkt.setText("Bestelling afgewerkt");
        bestellingAfgewerkt.setOnAction(event -> {
            try {
                // methode afgewerkt
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return bestellingAfgewerkt;
    }

    private Text setUpBestellingInfo() {
        String tekst = "";
        bestellingInfo = new Text(tekst);
        return bestellingInfo;
    }

    public void updateBestellingsInfo() {
        Bestelling bestelling = controller.kitchenView.getWachtrij().get(0);

        if (bestelling.containsDuplicates() != null) {
            //TODO: Louis fixt dit morgen wel
            // das voor broodjes die duplicates zijn te tonen als: aantal X broodjeinfo
        }

        String tekst = "Volgnummer bestelling: "+ bestelling.getVolgnummer()+ " -- Aantal broodjes: "+bestelling.getBestellijnen().size() + "\n";
        System.out.println(bestelling.containsDuplicates());
        bestellingInfo.setText(tekst);
    }

    private int countOccurances(ArrayList<Bestellijn> bestellijnArrayList, Bestellijn duplicate) {
        int counter = 0;
        for (Bestellijn bestellijn : bestellijnArrayList) {
            if (bestellijn.naamBroodje == duplicate.naamBroodje && bestellijn.getNamenBeleg() == duplicate.getNamenBeleg()) {
                counter++;
            }
        }
        return counter;
    }

    private Text setUpAantalBestellingen() {
        aantalBestellingen = new Text("Aantal bestellingen inwachtrij: 0");
        aantalBestellingen.setVisible(true);
        return aantalBestellingen;
    }

    public void updateCounter() {
        this.counter = controller.kitchenView.getWachtrij().size();
        String tekst = "Aantal bestellingen in wachtrij: "+this.counter;
        this.aantalBestellingen.setText(tekst);
    }
}
