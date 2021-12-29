package view.order.panes;

import controller.BestelViewController;
import javafx.geometry.Insets;
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

/**
 * Deze klasse weergeeft de "Afsluiting bestelling"-pane van de Order-view.
 */
public class OrderAfsluitingBestelling extends GridPane {
    public BestelViewController controller;
    Button afsluitKnop1;
    Button betaalKnop;
    Button keukenKnop;
    Text bedragTekst;

    public OrderAfsluitingBestelling(BestelViewController controller){
        afsluitKnop1 = new Button();
        this.controller = controller;
        this.setPadding(new Insets(10,0,10,20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, new CornerRadii(10), Insets.EMPTY)));
        this.add(this.setAfsluitKnop(),0, 0);
        this.add(this.setUpBedragTekst(), 1,0);
        this.add(this.setUpBetaalKnop(), 3,0);
        this.add(this.setUpKeukenKnop(), 4,0);
    }

    private Button setAfsluitKnop() {
        afsluitKnop1.setText("Afsluiten Bestelling");
        afsluitKnop1.setDisable(true);
        afsluitKnop1.setOnAction(event -> {
            try {
                this.controller.afsluitenBestelling();
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return afsluitKnop1;
    }
    private Text setUpBedragTekst() {
        this.bedragTekst = new Text("Te betalen: €0.0");
        this.bedragTekst.setVisible(true);
        return this.bedragTekst;
    }

    public void updateBedrag(double bedrag) {
        String tekst = "Te betalen: €" + bedrag;
        this.bedragTekst.setText(tekst);
    }

    private Button setUpBetaalKnop() {
        this.betaalKnop = new Button("Betaal");
        this.betaalKnop.setOnAction(e -> {
                try {
                    controller.betaalBestelling();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (BiffException biffException) {
                    biffException.printStackTrace();
                }
        });
        this.betaalKnop.setDisable(true);
        return this.betaalKnop;
    }

    private Button setUpKeukenKnop() {
        this.keukenKnop = new Button("Naar Keuken");
        this.keukenKnop.setOnAction(e -> {
            try {
                controller.stuurBestellingNaarKeuken();
            } catch (BiffException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        this.keukenKnop.setDisable(true);
        return this.keukenKnop;
    }

    public void update(Bestelling bestelling) {
        if (bestelling.getState().getClass().getName().contains("InWacht")) {
            this.afsluitKnop1.setDisable(true);
            this.betaalKnop.setDisable(true);
            this.keukenKnop.setDisable(true);
            this.updateBedrag(0);
        }
        if (bestelling.getState().getClass().getName().contains("InBestelling")) {
            this.afsluitKnop1.setDisable(false);
        }
        if (bestelling.getState().getClass().getName().contains("Afgesloten")) {
            this.betaalKnop.setDisable(false);
            this.afsluitKnop1.setDisable(true);
        }
        if (bestelling.getState().getClass().getName().contains("Betaald")) {
            this.afsluitKnop1.setDisable(true);
            this.betaalKnop.setDisable(true);
            this.keukenKnop.setDisable(false);
            this.updateBedrag(0);
        }
        if (bestelling.getState().getClass().getName().contains("InKeuken")){
            this.keukenKnop.setDisable(true);
        }
    }
}
