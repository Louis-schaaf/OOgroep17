package view.kitchen.panes;

import controller.KeukenViewController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Bestellijn;
import model.Bestelling;

import java.util.ArrayList;
import java.util.List;

/**
 * Deze klasse geeft een overzicht van de beschikbare knopen en bijhorende functies van de Kitchen-view.
 */
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
        this.setHgap(30);
        this.setVgap(10);
        this.add(this.setUpVolgendeBestellingButton(),0,0);
        this.add(this.setUpBestellingAfgewerkt(),1,0);
        this.setHgap(60);
        this.add(this.setUpAantalBestellingen(),0,1);
        this.setHgap(60);
        this.add(this.setUpBestellingInfo(),0,2);
        bestellingInfo.setVisible(false);
    }

    private Button setUpVolgendeBestellingButton() {
        volgendeBestelling.setText("Volgende bestelling");
        this.volgendeBestelling.setDisable(true);
        volgendeBestelling.setOnAction(event -> {
            try {
                bestellingInfo.setVisible(true);
                this.volgendeBestelling.setDisable(true);
                this.bestellingAfgewerkt.setDisable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return volgendeBestelling;
    }

    private Button setUpBestellingAfgewerkt() {
        bestellingAfgewerkt.setText("Bestelling afgewerkt");
        this.bestellingAfgewerkt.setDisable(true);
        bestellingAfgewerkt.setOnAction(event -> {
            try {
                this.bestellingInfo.setVisible(false);
                this.bestellingAfgewerkt.setDisable(true);
                this.controller.bestellingAfgewerkt();
                if (this.controller.getBetaaldeBestellingen().size() > 0) {
                    this.updateBestellingsInfo();
                    this.volgendeBestelling.setDisable(false);
                }
                this.updateCounter();
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
        Bestelling bestelling = this.controller.getBetaaldeBestellingen().get(0);
        int aantal = bestelling.getBestellijnen().size();
        String broodjesInfo = "";
        while (bestelling.containsDuplicates() != null) {
            Bestellijn duplicate = bestelling.containsDuplicates();
            ArrayList<Bestellijn> duplicates = getDuplicates(bestelling.bestellijnen, duplicate);
            broodjesInfo += duplicates.size()+"x "+duplicate.naamBroodje+": "+duplicate.getNamenBelegWithCount()+"\n";
            bestelling.getBestellijnen().removeAll(duplicates);
        }
        for (Bestellijn bestellijn : bestelling.getBestellijnen()) {
            broodjesInfo+= "1x "+bestellijn.naamBroodje+": "+ bestellijn.getNamenBeleg();
        }
        String tekst = "Volgnummer bestelling: "+ bestelling.getVolgnummer()+ " -- Aantal broodjes: "+aantal + "\n" +
                broodjesInfo;
        System.out.println(bestelling.containsDuplicates());
        bestellingInfo.setText(tekst);
    }

    private ArrayList<Bestellijn> getDuplicates(List<Bestellijn> bestellijnArrayList, Bestellijn duplicate) {
        ArrayList<Bestellijn> duplicates = new ArrayList<>();
        for (Bestellijn bestellijn : bestellijnArrayList) {
            if (bestellijn.naamBroodje.equals(duplicate.naamBroodje) && bestellijn.getNamenBeleg().equals(duplicate.getNamenBeleg())) {
                duplicates.add(bestellijn);
            }
        }
        return duplicates;
    }

    private Text setUpAantalBestellingen() {
        aantalBestellingen = new Text("Aantal bestellingen inwachtrij: 0");
        aantalBestellingen.setVisible(true);
        return aantalBestellingen;
    }

    public void updateCounter() {
        this.counter = this.controller.getBetaaldeBestellingen().size();
        String tekst = "Aantal bestellingen in wachtrij: " + this.counter;
        this.aantalBestellingen.setText(tekst);
    }

    public void update() {
        if (this.controller.getBetaaldeBestellingen().size() > 0) {
            this.volgendeBestelling.setDisable(false);
        }
    }
}
