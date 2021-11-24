package model.database;

import model.Broodje;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BroodjesInMemory {
    private ArrayList<Broodje> broodjesDB = new ArrayList<>();


    public void readFile() {
        String filePath = "src/bestanden/broodjes.txt";
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // TODO: splitten op , zodra broodje class af is
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
