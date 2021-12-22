package model.database.loadSaveStrategies;

public class LoadSaveStrategyFactory {
    String fileTypeUser;
    public LoadSaveStrategyFactory(){
    }
    public Object createLoadSaveStrategy(){;
        Object terug = null;
        String fileUserFullName = "model.database.loadSaveStrategies.Beleg"+fileTypeUser+"LoadSaveStrategy";
        Class loadSaveStrategyEnum = null;
        try {
            loadSaveStrategyEnum = Class.forName(fileUserFullName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            terug = loadSaveStrategyEnum.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return terug;
    }
    public void init(String fileTypeUser){
        setFileTypeUser(fileTypeUser);
    }
    public void setFileTypeUser(String fileTypeUser) {
        this.fileTypeUser = fileTypeUser;
    }
}
