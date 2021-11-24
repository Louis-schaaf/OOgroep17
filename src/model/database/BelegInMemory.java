package model.database;

import model.Beleg;
import model.Broodje;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BelegInMemory {

    private Map<String, Beleg> belegDB = new HashMap<>();

    public void readFile() {
        String filePath = "src/bestanden/broodjes.txt";
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
                belegDB.put(arr[0],new Beleg(arr[0],Double.parseDouble(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3])));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Map<String, Beleg> getBelegDB() {
        return this.belegDB;
    }
}
