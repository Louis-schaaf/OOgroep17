package model.database.loadSaveStrategies;

import java.lang.reflect.Constructor;

public class LoadSaveStrategyFactory {
    public static LoadSaveStrategy createLoadSaveStrategy(String type, Object... args){
        LoadSaveStrategy instance = null;

        LoadSaveStrategyEnum loadSaveStrategyEnum = LoadSaveStrategyEnum.valueOf(type);
        String klassenaam = loadSaveStrategyEnum.getKlasseNaam();
        Class<?>[] dataTypes = new Class[args.length];
        int tel = 0;
        for (Object object:args){
            dataTypes[tel++] = object.getClass();
        }
        try {
            Class<?> clazz = Class.forName(klassenaam);
            Constructor<?> constructor = clazz.getConstructor(dataTypes);
            instance = (LoadSaveStrategy) constructor.newInstance(args);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }return instance;
    }
}
