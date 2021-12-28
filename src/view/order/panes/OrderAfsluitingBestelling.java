package view.order.panes;

import controller.BestelViewController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import jxl.read.biff.BiffException;
import model.Bestelling;

import java.io.IOException;
import java.util.List;

public class OrderAfsluitingBestelling extends GridPane {
    public BestelViewController controller;
    Button afsluitKnop;
    Button betaalKnop;
    Button keukenKnop;
    Text bedragTekst;
    double bedrag = 0;

    public OrderAfsluitingBestelling(BestelViewController controller){
        afsluitKnop = new Button();
        this.controller = controller;
        this.setPadding(new Insets(10,0,10,20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.add(this.setUpAfsluitKnop(),0, 0);
        this.add(this.setUpBedragTekst(), 1,0);
        this.add(this.setUpBetaalKnop(), 3,0);
        this.add(this.setUpKeukenKnop(), 4,0);
        this.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, new CornerRadii(10), Insets.EMPTY)));
    }

    private Button setUpAfsluitKnop() {
        afsluitKnop.setText("Afsluiten Bestelling");
        afsluitKnop.setOnAction(event -> {
                try {
                    this.controller.afsluitenBestelling(); //TODO
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (BiffException biffException) {
                    biffException.printStackTrace();
                }
        });
        this.afsluitKnop.setDisable(true);
        return this.afsluitKnop;
    }
    private Text setUpBedragTekst() {
        this.bedragTekst = new Text("Te betalen: €...");
        this.bedragTekst.setVisible(true);
        return this.bedragTekst;
    }

    private void updateBedrag() {
        String tekst = "Te betalen: €" + this.bedrag;
        this.bedragTekst.setText(tekst);
    }

    private Button setUpBetaalKnop() {
        this.betaalKnop = new Button("Betaal");
        this.afsluitKnop.setOnAction(e -> {
                /*try {
                    controller.betaalBestelling(); //TODO
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (BiffException biffException) {
                    biffException.printStackTrace();
                }*/
        });
        this.betaalKnop.setDisable(true);
        return this.betaalKnop;
    }

    private Button setUpKeukenKnop() {
        this.keukenKnop = new Button("Naar Keuken");
        this.afsluitKnop.setOnAction(e -> {
                /*try {
                    controller.stuurBestellingNaarKeuken(); //TODO
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (BiffException biffException) {
                    biffException.printStackTrace();
                }*/
        });
        this.keukenKnop.setDisable(true);
        return this.keukenKnop;
    }

    public void update(Bestelling bestelling) {
        if (bestelling.getState().getClass().getName().contains("InWacht")) {
            this.afsluitKnop.setDisable(true);
            this.betaalKnop.setDisable(true);
            this.keukenKnop.setDisable(true);
        }
        if (bestelling.getState().getClass().getName().contains("InBestelling")) {
            this.afsluitKnop.setDisable(false);
        }
        if (bestelling.getState().getClass().getName().contains("Afgesloten")) {
            this.betaalKnop.setDisable(false);
            this.updateBedrag();
        }
        if (bestelling.getState().getClass().getName().contains("Betaald")) {
            this.afsluitKnop.setDisable(true);
            this.betaalKnop.setDisable(true);
            this.keukenKnop.setDisable(false);
        }
    }
}
