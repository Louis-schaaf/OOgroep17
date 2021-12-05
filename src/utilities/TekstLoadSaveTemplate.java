package utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class TekstLoadSaveTemplate<K,V>{

    public Map<K,V> load(File file) throws IOException {
        Map<K,V> returnMap = new HashMap<K,V>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line = reader.readLine();
            while (line != null && !line.trim().equals("")) {
                String[] tokens = line.split(",");
                V element = maakObject(tokens);
                K key = getKey(tokens);
                returnMap.put(key,element);
                line = reader.readLine();
            }
        }
        return returnMap;
    }

    public void write(File file, ArrayList<ArrayList<String>> args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < args.size(); i++) {
                writer.newLine();
                String[] list = (String[]) args.get(i).toArray();
                String line = list.toString();
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract V maakObject(String[] tokens);

    public abstract K getKey(String[] tokens);
}
