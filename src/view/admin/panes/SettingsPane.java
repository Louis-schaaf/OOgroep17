package view.admin.panes;

import com.sun.scenario.Settings;
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
import model.Instellingen;

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
        choiceBox.setValue(Instellingen.getKorting());
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
        loadSaveStrategy.setValue(Instellingen.getLoad());
        return loadSaveStrategy;
    }

    public void update(){
    }

    private Button setSaveButton() {
        buttonSave = new Button();
        buttonSave.setText("Save");
        buttonSave.setOnAction(event -> {
            Instellingen.setKorting(this.choiceBox.getValue().toString());
            Instellingen.setLoad(this.loadSaveStrategy.getValue().toString());
            this.controller.setProperties();
        });
        return buttonSave;
    }
}
