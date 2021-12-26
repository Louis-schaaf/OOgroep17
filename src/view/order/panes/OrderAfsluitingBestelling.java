package view.order.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class OrderAfsluitingBestelling extends GridPane {

    public OrderAfsluitingBestelling(){
        this.setPadding(new Insets(10,0,10,20));
        this.setHgap(30); //horizontal gap in pixels => that's what you are asking for
        this.setVgap(10); //vertical gap in pixels
        this.add(new Button("Afsluiten Bestelling"),0, 0);
        this.add(new Text("Te betalen"), 1,0);
        this.add(new Button("Betaal"), 3,0);
        this.add(new Button("Naar Keuken"), 4,0);
        this.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, new CornerRadii(10), Insets.EMPTY)));
}
    /*private Button setUpStartGameButton() {
        // Creating a Button
        button = new Button();
        // Setting text to the button
        button.setText("Start gokspel");

        // Setting events of the button
        button.setOnAction(e -> {
            Speler speler = this.getPlayerByName();
            Boolean isValidSaldo = this.checkSaldoInput();

            // Check if player and saldo are valid
            if (speler == null || !isValidSaldo)
                return;

            int saldo = Integer.parseInt(inputWager.getText());

            // Check if the player has enough money
            if (!speler.checkSaldo(saldo))
                return;

            try {
                controller.start(speler, saldo);
                button.setDisable(true);
                inputPlayerName.setDisable(true);
                inputWager.setDisable(true);
            } catch (IOException | BiffException ex) {
                ex.printStackTrace();
            }
        });

        return button;
    }

    /**
     * Gets the player by name
     * @return returns Speler if found
     */
}