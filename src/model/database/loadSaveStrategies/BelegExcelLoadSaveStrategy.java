package model.database.loadSaveStrategies;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.BelegSoort;
import model.Broodje;
import utilities.ExcelLoadSaveTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BelegExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy{
    File file = new File("src/bestanden/beleg.xls");

    public BelegExcelLoadSaveStrategy() {}

    public Map getData(File file){
        Map<String, BelegSoort> map = new HashMap<>();
        try {
            ArrayList<ArrayList<String>> arrayList = read(file);
            for (int i = 0; i != arrayList.size(); i++) {
                BelegSoort beleg = new BelegSoort(arrayList.get(i).get(0),Double.parseDouble(arrayList.get(i).get(1)),Integer.parseInt(arrayList.get(i).get(2)),Integer.parseInt(arrayList.get(i).get(3)));
                map.put(arrayList.get(i).get(0), beleg);
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map load() {
        return getData(file);
    }

    @Override
    public void save(List list) {
        ArrayList arrayList1 = new ArrayList<>();
        for (Object broodje : list) {
            Broodje brood = (Broodje) broodje;
            ArrayList arrayList = new ArrayList<String>();
            arrayList.add(brood.getName());
            arrayList.add(String.valueOf(brood.getSalePrice()));
            arrayList.add(String.valueOf(brood.getActualStock()));
            arrayList.add(String.valueOf(brood.getSoldAmount()));
            arrayList1.add(arrayList);
        }
        try {
            write(file, arrayList1);
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
