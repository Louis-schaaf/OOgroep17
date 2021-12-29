package view.admin.panes;

import controller.BestelViewController;
import controller.admin.InstellingenController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import jxl.read.biff.BiffException;
import model.Bestelling;

import java.io.IOException;

public class SettingsPane extends GridPane {
    public InstellingenController controller;
    private ChoiceBox choiceBox;
    private ChoiceBox loadSaveStrategy;
    private Text uitlegKorting;
    private Text uitlegLoadSave;
    Button buttonSave;

    public SettingsPane(InstellingenController instellingenController){
        this.controller = instellingenController;
        controller.setSettingsPane(this);
        this.setHgap(30);
        this.setVgap(10);
        this.setPadding(new Insets(10,10,10,20));
        this.add(this.setUpLoadSaveTextField(), 0,0);
        this.add(this.setUpLoadSaveStrategy(), 1,0);
        this.add(this.setUpKortingsTextField(),0,2);
        this.add(this.setUpChoiceBox(),1,2);
        this.add(this.setSaveButton(), 0,3);

    }

    // Zet een choicebox op en zet deze als standaardwaarde op disabled.
    private Node setUpChoiceBox() {
        choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("GOEDKOOPSTEGRATIS", "GEENKORTING", "TIENPROCENT");
        choiceBox.setValue("GEENKORTING");
        return choiceBox;
    }
    private Node setUpKortingsTextField(){
        this.uitlegKorting = new Text("Stel de standaardwaarde van de korting in:");
        return uitlegKorting;
    }
    private Node setUpLoadSaveTextField(){
        this.uitlegLoadSave = new Text("Kies de manier waarop de data wordt ingelezen:");
        return uitlegLoadSave;
    }
    private Node setUpLoadSaveStrategy() {
        loadSaveStrategy = new ChoiceBox();
        loadSaveStrategy.getItems().addAll("EXCEL", "TEKST");
        loadSaveStrategy.setValue("EXCEL");
        return loadSaveStrategy;
    }
    public void update(){

    }

    private Button setSaveButton() {
        buttonSave = new Button();
        buttonSave.setText("Save");
        buttonSave.setOnAction(event -> {
        });
        return buttonSave;
    }
    // Zet een orderButton van "Nieuwe Bestelling"
    // Wanneer deze wordt ingedrukt wordt er een nieuwe bestelling aangemaakt.
    // De knop wort daarna teruggegeven.
    /*private Button setUpOrderButton() {
        buttonNieuweBestelling.setText("Nieuwe bestelling");
        buttonNieuweBestelling.setOnAction(event -> {
            try {
                this.controller.startNieuweBestelling();
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return buttonNieuweBestelling;
    }

    private Text setUpVolgnummer() {
        volgnummerText = new Text("Volgnummer: 1");
        volgnummerText.setVisible(false);
        return volgnummerText;
    }

    private void updateVolgnummer() {
        String tekst = "Volgnummer: " + this.volgnummer;
        this.volgnummerText.setText(tekst);
        this.controller.getBestelling().setVolgnummer(volgnummer);
    }

    // De states worden met elkaar vergeleken.
    // Wanneer de state van de bestelling niet meer InWacht staat
    // moet de bestelknop gedisabled worden
    // en de choiceBox worden geanabled.
    public void update(Bestelling bestelling) {
        if (bestelling.getState().getClass().getName().contains("InWacht")) {
            buttonNieuweBestelling.setDisable(false);
            volgnummerText.setVisible(false);
            choiceBox.setDisable(true);
        }
        if (bestelling.getState().getClass().getName().contains("InBestelling")) {
            buttonNieuweBestelling.setDisable(true);
            this.updateVolgnummer();
            volgnummerText.setVisible(true);
            choiceBox.setDisable(false);
        }
        if (bestelling.getState().getClass().getName().contains("Afgesloten")) {
            buttonNieuweBestelling.setDisable(true);
            choiceBox.setDisable(true);
        }
        if (bestelling.getState().getClass().getName().contains("Betaald")) {
            buttonNieuweBestelling.setDisable(true);
            choiceBox.setDisable(true);
            this.volgnummer++;
        }
    }
    public void update() {
    }*/
}
