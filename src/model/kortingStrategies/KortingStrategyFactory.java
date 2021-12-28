package model.kortingStrategies;

import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;

import java.lang.reflect.Constructor;

public class KortingStrategyFactory {
    public static KortingStrategy createKortingStrategy(String type, Object... args) {
        KortingStrategy instance = null;

        KortingStrategyEnum kortingStrategyEnum = KortingStrategyEnum.valueOf(type);
        String klassenaam = kortingStrategyEnum.getKlasseNaam();
        Class<?>[] dataTypes = new Class[args.length];
        int tel = 0;
        for (Object object : args) {
            dataTypes[tel++] = object.getClass();
        }
        try {
            Class<?> clazz = Class.forName(klassenaam);
            Constructor<?> constructor = clazz.getConstructor(dataTypes);
            instance = (KortingStrategy) constructor.newInstance(args);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return instance;
    }
}
