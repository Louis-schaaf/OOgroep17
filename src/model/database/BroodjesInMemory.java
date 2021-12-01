package model.database;

import model.Broodje;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BroodjesInMemory {
    private Map<String, Broodje> broodjesDB = new HashMap<>();

    public void readFile() {
        String filePath = "src/bestanden/broodjes.txt";
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
                broodjesDB.put(arr[0],new Broodje(arr[0],Double.parseDouble(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3])));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Map<String, Broodje> getBroodjesDB() {
        return this.broodjesDB;
    }
}
